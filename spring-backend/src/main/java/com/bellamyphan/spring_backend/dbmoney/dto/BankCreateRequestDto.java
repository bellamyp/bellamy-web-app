package com.bellamyphan.spring_backend.dbmoney.dto;

import com.bellamyphan.spring_backend.dbmoney.entity.BankType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@NoArgsConstructor
@AllArgsConstructor
public class BankCreateRequestDto {

    @NotBlank(message = "Bank name is required")
    private String name;

    @NotNull(message = "Opening date is required")
    private LocalDate openingDate;

    private LocalDate closingDate;

    @NotNull(message = "Bank type is required")
    private BankType type;
}
