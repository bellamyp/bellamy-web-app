package com.bellamyphan.spring_backend.dbuser.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDto {

    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}
