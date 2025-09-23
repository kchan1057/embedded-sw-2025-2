package com.example.embededproject20252.controller;

import com.example.embededproject20252.dto.SerialNumRequestDto;
import com.example.embededproject20252.dto.SerialNumResponseDto;
import com.example.embededproject20252.service.SerialNumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/serial")
public class SerialNumController {

  private final SerialNumService serialNumService;

  @PostMapping
  public ResponseEntity<SerialNumResponseDto> addSerialNum(@RequestBody SerialNumRequestDto serialNumRequestDto){
    return ResponseEntity.ok(serialNumService.addSerialNum(serialNumRequestDto));
  }
}
