package com.bellamyphan.spring_backend.dbmoney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCreateResponseDto {

    private Double amount;
    private String notes;
    private LocalDate date;
    private String type;
    private String bankName;
}
