package com.localshare.history.entity;

import com.localshare.common.enums.TransferDirection;
import com.localshare.common.enums.TransferStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transfer_records")

public class TransferRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transferId;

    private String fileName;

    private long fileSize;

    private String senderDeviceName;

    private String receiverDeviceName;

    @Enumerated(EnumType.STRING)
    private TransferStatus status;

    @Enumerated(EnumType.STRING)
    private TransferDirection direction;

    private LocalDateTime timestamp;

    public TransferRecord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getSenderDeviceName() {
        return senderDeviceName;
    }

    public void setSenderDeviceName(String senderDeviceName) {
        this.senderDeviceName = senderDeviceName;
    }

    public String getReceiverDeviceName() {
        return receiverDeviceName;
    }

    public void setReceiverDeviceName(String receiverDeviceName) {
        this.receiverDeviceName = receiverDeviceName;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }

    public TransferDirection getDirection() {
        return direction;
    }

    public void setDirection(TransferDirection direction) {
        this.direction = direction;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
