package com.bellamyphan.spring_backend.dbuser.controller;

import com.bellamyphan.spring_backend.dbuser.dto.UserCreateRequestDto;
import com.bellamyphan.spring_backend.dbuser.dto.UserCreateResponseDto;
import com.bellamyphan.spring_backend.dbuser.entity.User;
import com.bellamyphan.spring_backend.dbuser.dto.UserMapper;
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
    public ResponseEntity<UserCreateResponseDto> createUser(@Valid @RequestBody UserCreateRequestDto user) {
        User requestedUser = userMapper.toEntity(user);
        Boolean isDemo = user.getIsDemo();
        User createdUser = userService.saveUser(requestedUser, isDemo);
        return ResponseEntity.ok(userMapper.toResponseDto(createdUser));
    }

}
