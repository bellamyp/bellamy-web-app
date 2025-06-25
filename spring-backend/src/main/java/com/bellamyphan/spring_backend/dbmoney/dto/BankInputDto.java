package com.bellamyphan.spring_backend.dbmoney.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@NoArgsConstructor
@AllArgsConstructor
public class BankInputDto {

    @NotBlank(message = "Bank ID is required")
    private Long id;
    
    @NotBlank(message = "Bank name is required")
    private String name;
}
