package com.localshare.handshake.cleanup;

import com.localshare.handshake.service.HandshakeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HandshakeCleanupTask {
    private final HandshakeService handshakeService;

    public HandshakeCleanupTask(HandshakeService handshakeService) {
        this.handshakeService = handshakeService;
    }

    @Scheduled(fixedDelay = 10000)
    public void cleanup() {
        handshakeService.removeExpiredRequests();
    }
}
