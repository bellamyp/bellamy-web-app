package com.bellamyphan.spring_backend.dbuser.dto;

import com.bellamyphan.spring_backend.dbuser.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserCreateResponseDto toResponseDto(User user) {
        return new UserCreateResponseDto(
                user.getUsername(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    public User toEntity(UserCreateRequestDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        return user;
    }
}
