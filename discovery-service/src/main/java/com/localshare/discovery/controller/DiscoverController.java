package com.localshare.discovery.controller;

import com.localshare.common.constants.ApiPaths;
import com.localshare.discovery.model.DeviceInfoModel;
import com.localshare.discovery.service.DiscoveryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.DISCOVERY_BASE)
public class DiscoverController {
    private final DiscoveryService discoveryService;

    public DiscoverController(DiscoveryService discoveryService) {
        this.discoveryService = discoveryService;
    }

    @GetMapping("/devices")
    public List<DeviceInfoModel> getDiscoveredDevices() {
        return discoveryService.getDiscoveredDevices();
    }
}
