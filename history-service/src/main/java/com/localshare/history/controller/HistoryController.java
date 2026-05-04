package com.localshare.history.controller;

import com.localshare.common.constants.ApiPaths;
import com.localshare.common.dto.HistoryRecordDTO;
import com.localshare.history.service.HistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.HISTORY_BASE)
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping
    public HistoryRecordDTO saveRecord(@RequestBody HistoryRecordDTO historyRecordDTO) {
        return historyService.saveRecord(historyRecordDTO);
    }

    @GetMapping
    public List<HistoryRecordDTO> getAllRecords() {
        return historyService.getAllRecords();
    }

    @GetMapping("/{transferId}")
    public HistoryRecordDTO getRecordsByTransferId(@PathVariable String transferId) {
        return historyService.getRecordByTransferId(transferId);
    }

    @DeleteMapping
    public void deleteAllRecords() {
        historyService.deleteAllRecords();
    }
}
