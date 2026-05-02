package com.localshare.common.dto;

import com.localshare.common.enums.DeviceType;

import java.time.LocalDateTime;

public record DeviceDTO(
        String deviceId,
        String deviceName,
        DeviceType deviceType,
        String ipAddress,
        int port,
        LocalDateTime lastSeen
) {
}
