package com.localshare.history.repository;

import com.localshare.history.entity.TransferRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransferRecordRepository extends JpaRepository<TransferRecord, Long> {

    Optional<TransferRecord> findByTransferId(String transferId);
}
