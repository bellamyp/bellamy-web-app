package com.bellamyphan.spring_backend.dbmoney.dto;

import com.bellamyphan.spring_backend.dbmoney.entity.Bank;
import org.springframework.stereotype.Component;

@Component
public class BankMapper {

    public BankCreateResponseDto toBankCreateResponseDto(Bank bank) {
        return new BankCreateResponseDto(
                bank.getName(),
                bank.getType().getType()
        );
    }

    public BankInputDto toBankInputDto(Bank bank) {
        return new BankInputDto(
                bank.getId(),
                bank.getName()
        );
    }

    public Bank toEntity(BankCreateRequestDto bankCreateRequestDto) {
        Bank bank = new Bank();
        bank.setName(bankCreateRequestDto.getName());
        bank.setOpeningDate(bankCreateRequestDto.getOpeningDate());
        bank.setClosingDate(bankCreateRequestDto.getClosingDate());
        bank.setType(bankCreateRequestDto.getType());
        return bank;
    }
}
