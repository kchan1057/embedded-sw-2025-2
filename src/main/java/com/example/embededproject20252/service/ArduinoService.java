package com.example.embededproject20252.service;

import com.example.embededproject20252.dto.ArduinoDataRequestDto;
import com.example.embededproject20252.dto.ArduinoDataResponseDto;

public interface ArduinoService {
  ArduinoDataResponseDto addArduinoData(ArduinoDataRequestDto arduinoDataRequestDto);
}
