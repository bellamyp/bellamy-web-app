package com.bellamyphan.spring_backend.dbmoney.controller;

import com.bellamyphan.spring_backend.dbmoney.dto.TransactionCreateRequestDto;
import com.bellamyphan.spring_backend.dbmoney.dto.TransactionCreateResponseDto;
import com.bellamyphan.spring_backend.dbmoney.entity.Transaction;
import com.bellamyphan.spring_backend.dbmoney.dto.TransactionMapper;
import com.bellamyphan.spring_backend.dbmoney.service.TransactionService;
import com.bellamyphan.spring_backend.dbuser.entity.User;
import com.bellamyphan.spring_backend.dbuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionMapper transactionMapper;
    private final TransactionService transactionService;
    private final UserService userService;

    // Create a new transaction
    @PostMapping
    public ResponseEntity<TransactionCreateResponseDto> createTransaction(
            @RequestBody TransactionCreateRequestDto transaction) {
        // Get username by the jwt token, then retrieve user from the database
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserName(username);
        // Save the new transaction to the database
        Transaction requestedTransaction = transactionMapper.toEntity(transaction);
        Transaction createdTransaction = transactionService.createTransaction(requestedTransaction, user);
        TransactionCreateResponseDto responseDto = transactionMapper.toTransactionCreateResponseDto(createdTransaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
