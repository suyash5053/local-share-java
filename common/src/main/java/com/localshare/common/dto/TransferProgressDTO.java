package com.localshare.common.dto;

import com.localshare.common.enums.TransferStatus;

public record TransferProgressDTO(
        String transferId,
        long byteTransferred,
        long totalBytes,
        double percentComplete,
        long speedBytesPerSecond,
        TransferStatus status
) {

}
