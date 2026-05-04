package com.localshare.transfer.service;

import com.localshare.common.dto.TransferProgressDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NotificationClient {
    private final WebClient webClient;

    public NotificationClient(@Qualifier("notificationWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public void notifyTransferStatus(TransferProgressDTO progress) {
        String notificationUrl = "/api/v1/notification/transfer/status";
        webClient.post().uri(notificationUrl).bodyValue(progress).retrieve().bodyToMono(Void.class).block();
    }
}
