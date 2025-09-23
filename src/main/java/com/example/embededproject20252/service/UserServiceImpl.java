package com.example.embededproject20252.service;

import com.example.embededproject20252.domain.User;
import com.example.embededproject20252.dto.RegisterRequestDto;
import com.example.embededproject20252.dto.RegisterResponseDto;
import com.example.embededproject20252.repository.SerialNumRepository;
import com.example.embededproject20252.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;
  private final CoolsmsService coolsmsService;
  private final SerialNumRepository serialNumRepository;

  @Transactional
  public RegisterResponseDto registerUser(RegisterRequestDto registerRequestDto) {
    User user = User.builder()
                    .serialNum(registerRequestDto.serialNum())
                    .name(registerRequestDto.name())
                    .phoneNumber(registerRequestDto.phoneNumber())
                    .build();

    User saved = userRepository.save(user);
    return toDto(saved);
  }

  public RegisterResponseDto toDto(User user){
    return new RegisterResponseDto(
        user.getSerialNum(),
        user.getName(),
        user.getPhoneNumber()
    );
  }

}
