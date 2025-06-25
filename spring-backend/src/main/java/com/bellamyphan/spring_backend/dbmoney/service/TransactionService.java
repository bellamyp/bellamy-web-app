package com.bellamyphan.spring_backend.dbmoney.service;

import com.bellamyphan.spring_backend.dbmoney.entity.Transaction;
import com.bellamyphan.spring_backend.dbmoney.repository.TransactionRepository;
import com.bellamyphan.spring_backend.dbuser.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Transactional
    public Transaction createTransaction(Transaction transaction, User user) {
        transaction.setUserId(user.getId());
        return transactionRepository.save(transaction);
    }
}
