package com.bellamyphan.spring_backend.dbmoney.controller;

import com.bellamyphan.spring_backend.dbmoney.entity.TransactionType;
import com.bellamyphan.spring_backend.dbmoney.repository.TransactionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions/types")
@RequiredArgsConstructor
public class TransactionTypeController {

    private final TransactionTypeRepository transactionTypeRepository;

    // Get all bank types
    @GetMapping
    public List<TransactionType> getAllTransactionTypes() {
        return transactionTypeRepository.findAll();
    }
}