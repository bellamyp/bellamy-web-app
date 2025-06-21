package com.bellamyphan.spring_backend.dbmoney.mapper;

import com.bellamyphan.spring_backend.dbmoney.dto.BankCreateRequestDto;
import com.bellamyphan.spring_backend.dbmoney.dto.BankCreateResponseDto;
import com.bellamyphan.spring_backend.dbmoney.entity.Bank;
import org.springframework.stereotype.Component;

@Component
public class BankMapper {

    public BankCreateResponseDto toResponseDto(Bank bank) {
        return new BankCreateResponseDto(
                bank.getName(),
                bank.getType().getType()
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
