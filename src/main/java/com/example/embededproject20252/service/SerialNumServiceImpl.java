package com.example.embededproject20252.service;

import com.example.embededproject20252.domain.SerialNum;
import com.example.embededproject20252.dto.SerialNumRequestDto;
import com.example.embededproject20252.dto.SerialNumResponseDto;
import com.example.embededproject20252.repository.SerialNumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SerialNumServiceImpl implements SerialNumService{

  private final SerialNumRepository serialNumRepository;
  @Override
  public SerialNumResponseDto addSerialNum(SerialNumRequestDto serialNumRequestDto) {

   SerialNum serialNum = new SerialNum(serialNumRequestDto.serialNum());
   SerialNum saved = serialNumRepository.save(serialNum);

   return toDto(saved);
  }

  private SerialNumResponseDto toDto(SerialNum serialNum){
    return new SerialNumResponseDto(
        serialNum.getId(),
        serialNum.getSerialNum()
    );
  }
}
