package com.localshare.handshake.service;

import com.localshare.common.dto.TransferRequestDTO;
import com.localshare.common.dto.TransferResponseDTO;
import com.localshare.common.enums.TransferStatus;
import com.localshare.common.exception.TransferNotFoundException;
import com.localshare.handshake.model.PendingRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class HandshakeService {

    private final ConcurrentHashMap<String, PendingRequest> pendingRequests = new ConcurrentHashMap<>();

    public TransferResponseDTO requestHandshake(TransferRequestDTO request) {
        String transferId = UUID.randomUUID().toString();

        PendingRequest pendingRequest = new PendingRequest(
                transferId,
                request,
                TransferStatus.PENDING,
                null,
                LocalDateTime.now()
        );

        pendingRequests.put(transferId, pendingRequest);

        return new TransferResponseDTO(
                transferId,
                TransferStatus.PENDING,
                null,
                "Transfer request created successfully"
        );
    }

    public TransferResponseDTO respondToRequest(String transferId, boolean accepted) {
        String token = null;
        TransferStatus status;
        String message = accepted ? "Accepted" : "Rejected";

        PendingRequest pending = pendingRequests.get(transferId);
        if (pending == null) {
            throw new TransferNotFoundException("Transfer not found");
        }

        if (accepted) {
            token = UUID.randomUUID().toString();
            status = TransferStatus.ACCEPTED;
        } else {
            status = TransferStatus.REJECTED;
        }

        pending.setStatus(status);
        pending.setToken(token);

        return new TransferResponseDTO(
                transferId,
                pending.getStatus(),
                pending.getToken(),
                message
        );
    }

    public void removeExpiredRequests() {
        pendingRequests.entrySet().removeIf(entry ->
                LocalDateTime.now().minusSeconds(30).isAfter(entry.getValue().getCreatedAt())
        );
    }
}
