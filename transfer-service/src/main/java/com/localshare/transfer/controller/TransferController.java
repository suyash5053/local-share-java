package com.localshare.transfer.controller;

import com.localshare.common.constants.ApiPaths;
import com.localshare.common.dto.TransferProgressDTO;
import com.localshare.common.dto.ValidateTokenRequestDTO;
import com.localshare.transfer.service.TransferService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(ApiPaths.TRANSFER_BASE)
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/send")
    public TransferProgressDTO sendFile(@RequestParam String transferId, @RequestParam String token, @RequestParam MultipartFile file, @RequestParam String senderDeviceName) {
        ValidateTokenRequestDTO validateTokenRequest = new ValidateTokenRequestDTO(
                transferId, token
        );
        return transferService.sendFile(file, validateTokenRequest,  senderDeviceName);
    }
}
