package com.bellamyphan.spring_backend.dbmoney.controller;

import com.bellamyphan.spring_backend.dbmoney.dto.BankCreateRequestDto;
import com.bellamyphan.spring_backend.dbmoney.dto.BankCreateResponseDto;
import com.bellamyphan.spring_backend.dbmoney.dto.BankInputDto;
import com.bellamyphan.spring_backend.dbmoney.entity.Bank;
import com.bellamyphan.spring_backend.dbmoney.dto.BankMapper;
import com.bellamyphan.spring_backend.dbmoney.entity.BankTypeEnum;
import com.bellamyphan.spring_backend.dbmoney.service.BankService;
import com.bellamyphan.spring_backend.dbuser.entity.User;
import com.bellamyphan.spring_backend.dbuser.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
@RequiredArgsConstructor
public class BankController {

    private static final Logger logger = LoggerFactory.getLogger(BankController.class);
    private final BankService bankService;
    private final UserService userService;
    private final BankMapper bankMapper;

    @PostMapping
    ResponseEntity<BankCreateResponseDto> createNewBank(@Valid @RequestBody BankCreateRequestDto bankCreateRequestDto) {
        // Get username by the jwt token, then retrieve user from the database
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserName(username);
        // Save the new bank to the database
        Bank requestBank = bankMapper.toEntity(bankCreateRequestDto);
        Bank newBank = bankService.createNewBank(requestBank, user.getId());
        logger.info("Username {} created a new bank: {}", username, newBank);
        // Return the response with the created bank
        BankCreateResponseDto responseDto = bankMapper.toBankCreateResponseDto(newBank);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    ResponseEntity<List<BankInputDto>> getAllBanksByType(
            @RequestParam(required = false) BankTypeEnum bankTypeEnum) {
        // Hold the result list
        List<BankInputDto> bankInputDtos;
//        // Debug:
//        System.out.println(">>> Received param: " + bankTypeEnum);
        // Get banks by type if bankType is provided, otherwise get all banks
        if (bankTypeEnum != null) {
            bankInputDtos = bankService.getBanksByAuthenticatedUser(bankTypeEnum);
        } else {
            bankInputDtos = bankService.getBanksByAuthenticatedUser();
        }
        // Return the list of banks
        return ResponseEntity.ok(bankInputDtos);
    }
}
