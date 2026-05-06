package com.localshare.transfer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    private final HandshakeClientProperties handshakeClientProperties;
    private final HistoryClientProperties historyClientProperties;
    private final NotificationClientProperties notificationClientProperties;

    public WebClientConfig(HandshakeClientProperties handshakeClientProperties, HistoryClientProperties historyClientProperties, NotificationClientProperties notificationClientProperties) {
        this.handshakeClientProperties = handshakeClientProperties;
        this.historyClientProperties = historyClientProperties;
        this.notificationClientProperties = notificationClientProperties;
    }

    @Bean("handshakeWebClient")
    public WebClient handshakeWebClient() {
        return WebClient.builder().baseUrl(handshakeClientProperties.getServiceUrl()).build();
    }

    @Bean("historyWebClient")
    public WebClient historyWebClient() {
        return WebClient.builder().baseUrl(historyClientProperties.getServiceUrl()).build();
    }

    @Bean("notificationWebClient")
    public WebClient notificationWebClient() {
        return WebClient.builder().baseUrl(notificationClientProperties.getServiceUrl()).build();
    }
}
