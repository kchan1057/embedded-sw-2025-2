package com.example.embededproject20252.service;

import com.example.embededproject20252.dto.RegisterRequestDto;
import com.example.embededproject20252.dto.RegisterResponseDto;

public interface UserService {

  RegisterResponseDto registerUser(RegisterRequestDto registerRequestDto);
}
