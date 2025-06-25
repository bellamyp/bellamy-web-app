package com.bellamyphan.spring_backend.dbmoney.dto;

import com.bellamyphan.spring_backend.dbmoney.entity.Bank;
import com.bellamyphan.spring_backend.dbmoney.entity.Transaction;
import com.bellamyphan.spring_backend.dbmoney.entity.TransactionType;
import com.bellamyphan.spring_backend.dbmoney.service.BankService;
import com.bellamyphan.spring_backend.dbmoney.service.TransactionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionMapper {

    private final BankService bankService;
    private final TransactionTypeService transactionTypeService;

    public Transaction toEntity(TransactionCreateRequestDto transactionCreateRequestDto) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionCreateRequestDto.getAmount());
        Bank bank = bankService.findBankById(transactionCreateRequestDto.getBankId());
        transaction.setBank(bank);
        transaction.setDate(transactionCreateRequestDto.getDate());
        transaction.setNotes(transactionCreateRequestDto.getNotes());
        TransactionType transactionType = transactionTypeService
                .findTypeByString(transactionCreateRequestDto.getType());
        transaction.setType(transactionType);
        return transaction;
    }

    public TransactionCreateResponseDto toTransactionCreateResponseDto(Transaction transaction) {
        TransactionCreateResponseDto responseDto = new TransactionCreateResponseDto();
        responseDto.setAmount(transaction.getAmount());
        responseDto.setNotes(transaction.getNotes());
        responseDto.setDate(transaction.getDate());
        responseDto.setType(transaction.getType().getType());
        if (transaction.getBank() != null) {
            responseDto.setBankName(transaction.getBank().getName());
        } else {
            responseDto.setBankName("No bank associated");
        }
        return responseDto;
    }
}
