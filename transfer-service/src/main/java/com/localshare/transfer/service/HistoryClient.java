package com.localshare.transfer.service;

import com.localshare.common.dto.HistoryRecordDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class HistoryClient {
    private final WebClient webClient;

    public HistoryClient(@Qualifier("historyWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public void saveRecord(HistoryRecordDTO history) {
        String historyUrl = "/api/v1/history";
        webClient.post().uri(historyUrl).bodyValue(history).retrieve().bodyToMono(Void.class).block();
    }

}
