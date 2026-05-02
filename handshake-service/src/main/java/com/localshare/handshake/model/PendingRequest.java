package com.localshare.handshake.model;

import com.localshare.common.dto.TransferRequestDTO;
import com.localshare.common.enums.TransferStatus;

import java.time.LocalDateTime;

public class PendingRequest {
    private String transferId;
    private TransferRequestDTO request;
    private TransferStatus status;
    private String token;
    private LocalDateTime createdAt;

    public PendingRequest(
            String transferId, TransferRequestDTO request, TransferStatus status, String token, LocalDateTime createdAt
    ) {
        this.transferId = transferId;
        this.request = request;
        this.status = status;
        this.token = token;
        this.createdAt = createdAt;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public TransferRequestDTO getRequest() {
        return request;
    }

    public void setRequest(TransferRequestDTO request) {
        this.request = request;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
