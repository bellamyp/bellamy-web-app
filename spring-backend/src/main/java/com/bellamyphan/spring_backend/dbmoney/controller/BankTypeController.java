package com.bellamyphan.spring_backend.dbmoney.controller;

import com.bellamyphan.spring_backend.dbmoney.service.BankTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/banks/types")
@RequiredArgsConstructor
public class BankTypeController {

    private final BankTypeService bankTypeService;

    // Get all bank types
    @GetMapping
    public ResponseEntity<List<String>> getAllBankTypes() {
        List<String> bankTypes = bankTypeService.findAllBankTypes();
        return ResponseEntity.ok(bankTypes);
    }
}
