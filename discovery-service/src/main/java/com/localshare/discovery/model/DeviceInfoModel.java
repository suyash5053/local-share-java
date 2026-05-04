package com.localshare.discovery.model;

import com.localshare.common.enums.DeviceType;

import java.time.LocalDateTime;

public class DeviceInfoModel {
    private String deviceId;
    private String deviceName;
    private DeviceType deviceType;
    private String ipAddress;
    private int port;
    private LocalDateTime lastSeen;

    public DeviceInfoModel(String deviceId, String deviceName, DeviceType deviceType, String ipAddress, int port, LocalDateTime lastSeen) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.ipAddress = ipAddress;
        this.port = port;
        this.lastSeen = lastSeen;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public LocalDateTime getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }
}
