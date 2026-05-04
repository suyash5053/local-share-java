package com.localshare.history.service;

import com.localshare.common.dto.HistoryRecordDTO;
import com.localshare.common.exception.TransferNotFoundException;
import com.localshare.history.entity.TransferRecord;
import com.localshare.history.repository.TransferRecordRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryService {

    private final TransferRecordRepository transferRecordRepository;

    public HistoryService(TransferRecordRepository transferRecordRepository) {
        this.transferRecordRepository = transferRecordRepository;
    }

    private HistoryRecordDTO toDTO(TransferRecord record) {
        return new HistoryRecordDTO(
                record.getId(),
                record.getTransferId(),
                record.getFileName(),
                record.getFileSize(),
                record.getSenderDeviceName(),
                record.getReceiverDeviceName(),
                record.getStatus(),
                record.getDirection(),
                record.getTimestamp()
        );
    }

    public HistoryRecordDTO saveRecord(HistoryRecordDTO dto) {
        TransferRecord transferRecord = new TransferRecord();
        transferRecord.setTransferId(dto.transferId());
        transferRecord.setFileName(dto.fileName());
        transferRecord.setFileSize(dto.fileSize());
        transferRecord.setSenderDeviceName(dto.senderDeviceName());
        transferRecord.setReceiverDeviceName(dto.receiverDeviceName());
        transferRecord.setStatus(dto.status());
        transferRecord.setDirection(dto.direction());
        transferRecord.setTimestamp(dto.timestamp() != null ? dto.timestamp() : LocalDateTime.now());

        TransferRecord saved = transferRecordRepository.save(transferRecord);
        return toDTO(saved);
    }

    public List<HistoryRecordDTO> getAllRecords() {
        return transferRecordRepository.findAll(Sort.by(Sort.Direction.DESC, "timestamp")).stream().map(this::toDTO).toList();
    }

    public HistoryRecordDTO getRecordByTransferId(String transferId) {
        TransferRecord found = transferRecordRepository.findByTransferId(transferId).orElseThrow(() -> new TransferNotFoundException("Transfer not found"));
        return toDTO(found);
    }

    public void deleteAllRecords() {
        transferRecordRepository.deleteAll();
    }
}
