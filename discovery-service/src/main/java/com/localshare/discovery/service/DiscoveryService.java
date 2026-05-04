package com.localshare.discovery.service;

import com.localshare.common.enums.DeviceType;
import com.localshare.discovery.config.DiscoveryProperties;
import com.localshare.discovery.model.DeviceInfoModel;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DiscoveryService {
    private final DiscoveryProperties discoveryProperties;
    private JmDNS jmDNS;
    private final ConcurrentHashMap<String, DeviceInfoModel> discoveredDevices = new ConcurrentHashMap<>();
    private final String deviceId = UUID.randomUUID().toString();
    private static final Logger log = LoggerFactory.getLogger(DiscoveryService.class);

    public DiscoveryService(DiscoveryProperties discoveryProperties) {
        this.discoveryProperties = discoveryProperties;
    }

    @PostConstruct
    public void startDiscovery() {
        try {
            jmDNS =  JmDNS.create(InetAddress.getLocalHost());

            Map<String, String> props = new HashMap<>();
            props.put("deviceId",  deviceId);
            props.put("deviceType", discoveryProperties.getDeviceType().toString());

            ServiceInfo serviceInfo = ServiceInfo.create(
                    discoveryProperties.getServiceType(),
                    discoveryProperties.getDeviceName(),
                    8084,
                    0,
                    0,
                    props
            );

            jmDNS.registerService(serviceInfo);

            jmDNS.addServiceListener(discoveryProperties.getServiceType(), new ServiceListener() {
                @Override
                public void serviceAdded(ServiceEvent event) {
                    jmDNS.requestServiceInfo(event.getType(), event.getName());
                }

                @Override
                public void serviceRemoved(ServiceEvent event) {
                    String removedDeviceId = event.getInfo().getPropertyString("deviceId");
                    if (removedDeviceId != null) {
                        discoveredDevices.remove(removedDeviceId);
                        log.info("Removed device: {}", event.getName());
                    }
                }

                @Override
                public void serviceResolved(ServiceEvent event) {
                    ServiceInfo info = event.getInfo();
                    String resolvedDeviceId = info.getPropertyString("deviceId");
                    if (resolvedDeviceId == null || resolvedDeviceId.equals(deviceId)) return;
                    DeviceInfoModel device = new DeviceInfoModel(
                            resolvedDeviceId,
                            info.getName(),
                            DeviceType.valueOf(info.getPropertyString("deviceType")),
                            info.getHostAddresses()[0],
                            info.getPort(),
                            LocalDateTime.now()
                    );
                    discoveredDevices.put(resolvedDeviceId, device);
                    log.info("Device Discovered: {}", info.getName());
                }
            });
        } catch (IOException e) {
            log.error("Failed to start discovery", e);
            throw new RuntimeException("Failed to start discovery" + e.getMessage());
        }
    }

    @PreDestroy
    public void stopDiscovery() {
        try {
            jmDNS.unregisterAllServices();
            jmDNS.close();
        } catch (IOException e) {
            log.error("Failed to stop discovery", e);
            throw new RuntimeException("Failed to stop discovery" + e.getMessage());
        }
    }

    public List<DeviceInfoModel> getDiscoveredDevices() {
        return new ArrayList<>(discoveredDevices.values());
    }
}
