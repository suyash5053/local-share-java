package com.localshare.common.dto;

import jakarta.validation.constraints.NotNull;

public record ValidateTokenRequestDTO(
        @NotNull(message = "transfer id can not be null")
        String transferId,

        @NotNull(message = "token can not be null")
        String token
) {
}
