package com.bellamyphan.spring_backend.dbmoney.service;

import com.bellamyphan.spring_backend.dbmoney.entity.TransactionType;
import com.bellamyphan.spring_backend.dbmoney.entity.TransactionTypeEnum;
import com.bellamyphan.spring_backend.dbmoney.exception.TransactionTypeCreationException;
import com.bellamyphan.spring_backend.dbmoney.repository.TransactionTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionTypeService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionTypeService.class);
    private final TransactionTypeRepository transactionTypeRepository;

    public Optional<TransactionType> findById(Long id) {
        return transactionTypeRepository.findById(id);
    }

    @Transactional
    public void createFirstTransactionType() {
        try {
            for (TransactionTypeEnum typeEnum : TransactionTypeEnum.values()) {
                createTransactionTypeIfNotExists(typeEnum.getDisplayName());
            }
            logger.info("Default transaction types creation completed");
        } catch (DataAccessException dae) {
            logger.error("Database error while creating transaction types", dae);
            throw new TransactionTypeCreationException("Database error while creating transaction types", dae);
        } catch (Exception ex) {
            logger.error("Unexpected error while creating transaction types", ex);
            throw new TransactionTypeCreationException("Unexpected error while creating transaction types", ex);
        }
    }

    private void createTransactionTypeIfNotExists(String type) {
        transactionTypeRepository.findByType(type)
                .map(existingType -> {
                    logger.info("Transaction type '{}' already exists", type);
                    return existingType;
                })
                .orElseGet(() -> {
                    TransactionType transactionType = new TransactionType(type);
                    transactionTypeRepository.save(transactionType);
                    logger.info("Created transaction type: {}", type);
                    return transactionType;
                });
    }

}
