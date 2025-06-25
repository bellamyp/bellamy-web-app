package com.bellamyphan.spring_backend.dbmoney.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCreateRequestDto {

    @NotNull(message = "Amount is required")
    private Double amount;
    private Long bankId;
    @NotNull(message = "Date is required")
    private LocalDate date;
    private String notes;
    @NotBlank(message = "Type is required")
    private String type;

}
