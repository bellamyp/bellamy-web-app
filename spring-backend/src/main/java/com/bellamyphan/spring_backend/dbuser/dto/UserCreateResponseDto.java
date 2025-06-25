package com.bellamyphan.spring_backend.dbuser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateResponseDto {
    private String username;
    private String firstName;
    private String lastName;
}
