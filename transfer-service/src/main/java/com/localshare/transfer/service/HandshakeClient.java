package com.localshare.transfer.service;

import com.localshare.common.dto.ValidateTokenRequestDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class HandshakeClient {
    private final WebClient webClient;

    public HandshakeClient(@Qualifier("handshakeWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public boolean validateToken(ValidateTokenRequestDTO validateTokenRequest) {
        String handshakeUrl = "/api/v1/handshake/validate";
        return Boolean.TRUE.equals(webClient.post().uri(handshakeUrl).bodyValue(validateTokenRequest).retrieve().bodyToMono(boolean.class).block());
    }
}
