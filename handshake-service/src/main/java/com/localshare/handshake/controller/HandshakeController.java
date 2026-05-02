package com.localshare.handshake.controller;

import com.localshare.common.constants.ApiPaths;
import com.localshare.common.dto.TransferRequestDTO;
import com.localshare.common.dto.TransferResponseDTO;
import com.localshare.handshake.service.HandshakeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.HANDSHAKE_BASE)
public class HandshakeController {
    private final HandshakeService handshakeService;

    public HandshakeController(HandshakeService handshakeService) {
        this.handshakeService = handshakeService;
    }

    @PostMapping("/request")
    public TransferResponseDTO requestHandshake(@Valid @RequestBody TransferRequestDTO transferRequestDTO) {
        return handshakeService.requestHandshake(transferRequestDTO);
    }
}
