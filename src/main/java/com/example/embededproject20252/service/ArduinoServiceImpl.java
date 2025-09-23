package com.example.embededproject20252.service;

import com.example.embededproject20252.domain.ArduinoData;
import com.example.embededproject20252.domain.User;
import com.example.embededproject20252.dto.ArduinoDataRequestDto;
import com.example.embededproject20252.dto.ArduinoDataResponseDto;
import com.example.embededproject20252.repository.ArduinoDataRepository;
import com.example.embededproject20252.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArduinoServiceImpl implements ArduinoService {

  private final ArduinoDataRepository arduinoDataRepository;
  private final UserRepository userRepository;
  private final CoolsmsService coolsmsService;

  @Override
  @Transactional
  public ArduinoDataResponseDto addArduinoData(ArduinoDataRequestDto arduinoDataRequestDto) {
    String serialNum = arduinoDataRequestDto.serialNum();
    int motion = arduinoDataRequestDto.motion();

    User user = userRepository.findBySerialNum(serialNum)
                              .orElseThrow(() -> new IllegalArgumentException("등록된 사용자가 없습니다."));

    String phoneNumber = user.getPhoneNumber();
    coolsmsService.sendOneMessage(phoneNumber, user.getName(), motion);

    ArduinoData arduinoData = new ArduinoData(serialNum, motion);
    ArduinoData saved = arduinoDataRepository.save(arduinoData);

    return toDto(saved);
  }

  private ArduinoDataResponseDto toDto(ArduinoData arduinoData){
    return new ArduinoDataResponseDto(
        arduinoData.getId(),
        arduinoData.getSerialNum(),
        arduinoData.getMotion()
    );
  }
}
