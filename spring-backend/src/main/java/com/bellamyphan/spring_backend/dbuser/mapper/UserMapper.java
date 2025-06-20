package com.bellamyphan.spring_backend.dbuser.mapper;

import com.bellamyphan.spring_backend.dbuser.dto.CreateUserRequestDto;
import com.bellamyphan.spring_backend.dbuser.dto.CreateUserResponseDto;
import com.bellamyphan.spring_backend.dbuser.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public CreateUserResponseDto toResponseDto(User user) {
        return new CreateUserResponseDto(
                user.getUsername(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    public User toEntity(CreateUserRequestDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        return user;
    }
}
