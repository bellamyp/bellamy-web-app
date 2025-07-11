package com.bellamyphan.spring_backend.dbmoney.service;

import com.bellamyphan.spring_backend.dbmoney.dto.BankInputDto;
import com.bellamyphan.spring_backend.dbmoney.entity.Bank;
import com.bellamyphan.spring_backend.dbmoney.entity.BankType;
import com.bellamyphan.spring_backend.dbmoney.dto.BankMapper;
import com.bellamyphan.spring_backend.dbmoney.entity.BankTypeEnum;
import com.bellamyphan.spring_backend.dbmoney.repository.BankRepository;
import com.bellamyphan.spring_backend.dbuser.entity.User;
import com.bellamyphan.spring_backend.dbuser.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService {

    private final BankRepository bankRepository;
    private final BankTypeService bankTypeService;
    private final UserService userService;
    private final BankMapper bankMapper;

    // Save a bank
    @Transactional
    public Bank createNewBank(Bank bank, Long userId) {
        // Lookup existing BankType by name
        BankType bankType = bankTypeService.findByNameIgnoreCase(bank.getType().getType());
        bank.setId(null); // Ensure the ID is null for a new entity
        bank.setUserId(userId); // Set the userId from the authenticated user
        bank.setType(bankType);
        return bankRepository.save(bank);
    }

    public void saveAllByUserDemoService(List<Bank> banks) {
        bankRepository.saveAll(banks);
    }

    // Find by userId
    public List<Bank> getBanksByUserId(Long userId) {
        return bankRepository.findByUserId(userId).orElse(new LinkedList<>());
    }

    public List<BankInputDto> getBanksByAuthenticatedUser() {
        // Get user by the jwt token
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserName(username);
        // Get banks by user id
        List<Bank> banks = bankRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("No banks found for user id: " + user.getId()));
        // Map to dto
        return banks.stream()
                .map(bankMapper::toBankInputDto)
                .toList();
    }

    public List<BankInputDto> getBanksByAuthenticatedUser(BankTypeEnum bankTypeEnum) {
        // Get user by the jwt token
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserName(username);
        // Get banks by user id and bank type
        BankType bankType = bankTypeService.findByNameIgnoreCase(bankTypeEnum.getDisplayName());
        List<Bank> banks = bankRepository.findByUserIdAndType(user.getId(), bankType)
                .orElseThrow(() -> new RuntimeException("No banks found for user id: " + user.getId()
                        + " and bank type: " + bankTypeEnum));
        // Map to dto
        return banks.stream()
                .map(bankMapper::toBankInputDto)
                .toList();
    }

    public Bank findBankById(Long bankId) {
        return bankRepository.findById(bankId)
                .orElseThrow(() -> new RuntimeException("Bank not found with id: " + bankId));
    }
}
