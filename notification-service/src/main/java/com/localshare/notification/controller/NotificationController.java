package com.localshare.notification.controller;

import com.localshare.common.constants.ApiPaths;
import com.localshare.common.dto.DeviceDTO;
import com.localshare.common.dto.TransferProgressDTO;
import com.localshare.common.dto.TransferRequestDTO;
import com.localshare.notification.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.NOTIFICATION_BASE)
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/devices")
    public void notifyDeviceUpdate(@RequestBody List<DeviceDTO> devices) {
        notificationService.notifyDeviceUpdate(devices);
    }

    @PostMapping("/transfer/incoming")
    public void notifyIncomingTransfer(@RequestBody TransferRequestDTO transfer) {
        notificationService.notifyIncomingTransfer(transfer);
    }

    @PostMapping("/transfer/progress")
    public void notifyProgress(@RequestBody TransferProgressDTO progress) {
        notificationService.notifyTransferProgress(progress);
    }

    @PostMapping("/transfer/status")
    public void notifyTransferStatus(@RequestBody TransferProgressDTO status) {
        notificationService.notifyTransferStatus(status);
    }
}
