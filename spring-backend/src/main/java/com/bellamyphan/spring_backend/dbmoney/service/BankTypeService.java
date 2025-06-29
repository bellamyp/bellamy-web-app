package com.bellamyphan.spring_backend.dbmoney.service;

import com.bellamyphan.spring_backend.dbmoney.entity.BankType;
import com.bellamyphan.spring_backend.dbmoney.entity.BankTypeEnum;
import com.bellamyphan.spring_backend.dbmoney.exception.BankTypeCreationException;
import com.bellamyphan.spring_backend.dbmoney.repository.BankTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BankTypeService {

    private static final Logger logger = LoggerFactory.getLogger(BankTypeService.class);
    private final BankTypeRepository bankTypeRepository;

    public List<String> findAllBankTypes() {
        List<BankType> bankTypes = bankTypeRepository.findAll();
        return bankTypes.stream()
                .map(BankType::getType)
                .collect(Collectors.toList());
    }

    public BankType findByNameIgnoreCase(String bankType) {
        return bankTypeRepository.findByTypeIgnoreCase(bankType)
                .orElseThrow(() -> new BankTypeCreationException("BankType not found: " + bankType));
    }

    @Transactional
    public void createFirstBankType() {
        try {
            for (BankTypeEnum bankTypeEnum : BankTypeEnum.values()) {
                createBankTypeIfNotExists(bankTypeEnum.getType());
            }
        } catch (DataAccessException dae) {
            logger.error("Database error while creating bank types", dae);
            throw new BankTypeCreationException("Database error while creating bank types", dae);
        } catch (Exception ex) {
            logger.error("Unexpected error while creating bank types", ex);
            throw new BankTypeCreationException("Unexpected error while creating bank types", ex);
        }
    }

    private void createBankTypeIfNotExists(String bankType) {
        bankTypeRepository.findByType(bankType)
                .map(existingType -> {
                    logger.info("BankType '{}' already exists", bankType);
                    return existingType;
                })
                .orElseGet(() -> {
                    BankType type = new BankType(bankType);
                    bankTypeRepository.save(type);
                    logger.info("Created bank type: {}", bankType);
                    return type;
                });
    }
}
