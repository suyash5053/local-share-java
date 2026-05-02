package com.localshare.common.dto;

import com.localshare.common.enums.FileType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransferRequestDTO(

        @NotBlank(message="File name must not be blank")
        String fileName,

        @Positive(message = "File Size should always be positive")
        long fileSize,

        @NotNull(message = "File type must not be null")
        FileType fileType,

        @NotBlank(message = "Sender device id must not be blank")
        String senderDeviceId,

        @NotBlank(message = "Sender device name must not be blank")
        String senderDeviceName
){
}
