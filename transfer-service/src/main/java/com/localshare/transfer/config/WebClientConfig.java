package com.localshare.transfer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    private final HandshakeClientProperties handshakeClientProperties;

    public WebClientConfig(HandshakeClientProperties handshakeClientProperties) {
        this.handshakeClientProperties= handshakeClientProperties;
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(handshakeClientProperties.getServiceUrl()).build();
    }
}
