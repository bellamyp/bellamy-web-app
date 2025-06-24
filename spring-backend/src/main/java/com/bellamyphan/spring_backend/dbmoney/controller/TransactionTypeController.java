package com.bellamyphan.spring_backend.dbmoney.controller;

import com.bellamyphan.spring_backend.dbmoney.service.TransactionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions/types")
@RequiredArgsConstructor
public class TransactionTypeController {

    private final TransactionTypeService transactionTypeService;

    // Get all bank types
    @GetMapping
    public ResponseEntity<List<String>> getAllTransactionTypes() {
        List<String> types = transactionTypeService.findAllTransactionTypes();
        return ResponseEntity.ok(types);
    }
}