package com.localshare.common.dto;

import com.localshare.common.enums.TransferStatus;

public record TransferResponseDTO(
        String transferId,
        TransferStatus status,
        String token,
        String message
) {
}
