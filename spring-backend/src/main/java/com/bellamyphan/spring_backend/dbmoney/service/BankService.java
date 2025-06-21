package com.bellamyphan.spring_backend.dbmoney.service;

import com.bellamyphan.spring_backend.dbmoney.dto.BankCreateRequestDto;
import com.bellamyphan.spring_backend.dbmoney.entity.Bank;
import com.bellamyphan.spring_backend.dbmoney.entity.BankType;
import com.bellamyphan.spring_backend.dbmoney.mapper.BankMapper;
import com.bellamyphan.spring_backend.dbmoney.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService {

    private final BankRepository bankRepository;
    private final BankTypeService bankTypeService;
    private final BankMapper bankMapper;

    // Save a bank
    public Bank createNewBank(BankCreateRequestDto bankCreateRequestDto, Long userId) {
        Bank bank = bankMapper.toEntity(bankCreateRequestDto);
        // Lookup existing BankType by name
        BankType bankType = bankTypeService.findByNameIgnoreCase(bankCreateRequestDto.getType());
        bank.setId(null); // Ensure the ID is null for a new entity
        bank.setUserId(userId); // Set the userId from the authenticated user
        bank.setType(bankType);
        return bankRepository.save(bank);
    }

    // Find by userId
    public List<Bank> getBanksByUserId(Long userId) {
        return bankRepository.findByUserId(userId).orElse(new LinkedList<>());
    }

//    public List<BankDto> getBanksByAuthenticatedUser() {
//        // Get user by the jwt token
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Cannot find the user with username: " + username));
//        // Get banks by user id
//        List<Bank> banks = bankRepository.findByUserId(user.getId())
//                .orElseThrow(() -> new RuntimeException("No banks found for user id: " + user.getId()));
//        // Map to dto
//        return banks.stream()
//                .map(dtoMapperService::bankMappingToDto)
//                .toList();
//    }
}
