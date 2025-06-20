package com.bellamyphan.spring_backend.dbuser.controller;

import com.bellamyphan.spring_backend.dbuser.dto.CreateUserRequestDto;
import com.bellamyphan.spring_backend.dbuser.dto.CreateUserResponseDto;
import com.bellamyphan.spring_backend.dbuser.entity.User;
import com.bellamyphan.spring_backend.dbuser.mapper.UserMapper;
import com.bellamyphan.spring_backend.dbuser.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<CreateUserResponseDto> createUser(@Valid @RequestBody CreateUserRequestDto user) {
        User createdUser = userService.saveUser(user);
        return ResponseEntity.ok(userMapper.toResponseDto(createdUser));
    }

}
