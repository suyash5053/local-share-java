package com.localshare.notification.service;

import com.localshare.common.dto.DeviceDTO;
import com.localshare.common.dto.TransferProgressDTO;
import com.localshare.common.dto.TransferRequestDTO;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyDeviceUpdate(List<DeviceDTO> devices){
        messagingTemplate.convertAndSend("/topic/devices", devices);
    }

    public void notifyIncomingTransfer(TransferRequestDTO transfer) {
        messagingTemplate.convertAndSend("/topic/transfers/incoming", transfer);
    }

    public void notifyTransferProgress(TransferProgressDTO progress) {
        messagingTemplate.convertAndSend("/topic/transfers/progress/" + progress.transferId(), progress);
    }

    public void notifyTransferStatus(TransferProgressDTO status) {
        messagingTemplate.convertAndSend("/topic/transfers/status/" + status.transferId(), status);
    }
}
