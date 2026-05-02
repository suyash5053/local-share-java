package com.localshare.common.dto;

import com.localshare.common.enums.TransferDirection;
import com.localshare.common.enums.TransferStatus;

import java.time.LocalDateTime;

public record HistoryProgressDTO(
        Long id,
        String transferId,
        String fileName,
        long fileSize,
        String senderDeviceName,
        String receiverDeviceName,
        TransferStatus status,
        TransferDirection direction,
        LocalDateTime timestamp
) {
}
