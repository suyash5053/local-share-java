package com.localshare.handshake.controller;

import com.localshare.common.constants.ApiPaths;
import com.localshare.common.dto.TransferRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.HANDSHAKE_BASE)
public class HandshakeServiceController {

    @PostMapping("/request")
    public String handleTransferRequest(@RequestBody TransferRequestDTO request) {
        return  "Handshake request received";
    }
}
