package com.example.embededproject20252.controller;

import com.example.embededproject20252.dto.ArduinoDataRequestDto;
import com.example.embededproject20252.dto.ArduinoDataResponseDto;

import com.example.embededproject20252.service.ArduinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/arduino")
public class ArduinoDataController {

  private final ArduinoService arduinoService;

  @PostMapping
  public ResponseEntity<ArduinoDataResponseDto> addArduinoData(
      @RequestBody ArduinoDataRequestDto arduinoDataRequestDto
  ){
    return ResponseEntity.ok(arduinoService.addArduinoData(arduinoDataRequestDto));
  }
}
