package com.localshare.transfer.service;

import com.localshare.common.dto.TransferProgressDTO;
import com.localshare.common.dto.ValidateTokenRequestDTO;
import com.localshare.common.enums.TransferStatus;
import com.localshare.common.exception.FileSizeLimitExceededException;
import com.localshare.common.exception.InvalidTokenException;
import com.localshare.transfer.config.TransferProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class TransferService {
    private final HandshakeClient handshakeClient;
    private final TransferProperties transferProperties;

    public TransferService(HandshakeClient handshakeClient, TransferProperties transferProperties) {
        this.handshakeClient = handshakeClient;
        this.transferProperties = transferProperties;
    }

    public TransferProgressDTO sendFile(MultipartFile file, ValidateTokenRequestDTO validateTokenRequest) {

        boolean validation = handshakeClient.validateToken(validateTokenRequest);
        if (!validation) {
            throw new InvalidTokenException("Your request can not be validated");
        }

        if (file.getSize() > transferProperties.getMaxFileSize()) {
            throw new FileSizeLimitExceededException("The file you chose is too large");
        }

        String safeFileName = Paths.get(Objects.requireNonNull(file.getOriginalFilename())).getFileName().toString();

        try {
            Path saveDir = Paths.get(transferProperties.getSaveDirectory());
            Files.createDirectories(saveDir);

            Path targetPath = saveDir.resolve(safeFileName);

            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            return new TransferProgressDTO(
                    validateTokenRequest.transferId(),
                    file.getSize(),
                    file.getSize(),
                    100.0,
                    0L,
                    TransferStatus.COMPLETED
            );


        } catch (IOException e) {
            throw new RuntimeException("Failed to save file: " + e.getMessage());
        }

    }
}
