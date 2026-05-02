package com.localshare.common.dto;

import com.localshare.common.enums.FileType;

public record TransferRequestDTO(
        String transferId,
        String fileName,
        long fileSize,
        FileType fileType,
        String senderDeviceId,
        String senderDeviceName
){
}
