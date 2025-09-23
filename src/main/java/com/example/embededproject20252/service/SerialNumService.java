package com.example.embededproject20252.service;

import com.example.embededproject20252.dto.SerialNumRequestDto;
import com.example.embededproject20252.dto.SerialNumResponseDto;

public interface SerialNumService {

  SerialNumResponseDto addSerialNum(SerialNumRequestDto serialNumRequestDto);
}
