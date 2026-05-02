package com.localshare.common.dto;

import java.time.LocalDateTime;

public record ApiErrorDTO(
        String errorCode,
        String message,
        LocalDateTime timestamp,
        String path
) {
}
