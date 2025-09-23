package com.example.embededproject20252.controller;

import com.example.embededproject20252.dto.RegisterRequestDto;
import com.example.embededproject20252.dto.RegisterResponseDto;
import com.example.embededproject20252.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<RegisterResponseDto> registerUser(@Valid RegisterRequestDto registerRequestDto) {
    return ResponseEntity.ok(userService.registerUser(registerRequestDto));
  }
}