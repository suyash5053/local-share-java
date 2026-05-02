package com.localshare.handshake.service;

import com.localshare.common.dto.TransferRequestDTO;
import com.localshare.common.dto.TransferResponseDTO;
import com.localshare.common.enums.TransferStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HandshakeService {
    public TransferResponseDTO requestHandshake(TransferRequestDTO request) {
        return new TransferResponseDTO(
                UUID.randomUUID().toString(),
                TransferStatus.PENDING,
                "dummy-token",
                "Transfer request created"
        );
    }
}
