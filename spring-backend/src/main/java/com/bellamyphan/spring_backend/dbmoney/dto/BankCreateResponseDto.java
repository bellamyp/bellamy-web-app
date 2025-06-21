package com.bellamyphan.spring_backend.dbmoney.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@NoArgsConstructor
@AllArgsConstructor
public class BankCreateResponseDto {

    @NotBlank(message = "Bank name is required")
    private String name;

    @NotBlank(message = "Bank type is required")
    private String bankType;
}
