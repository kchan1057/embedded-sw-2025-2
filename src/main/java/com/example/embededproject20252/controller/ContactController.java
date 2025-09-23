package com.example.embededproject20252.controller;

import com.example.embededproject20252.service.CoolsmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ContactController {

  private final CoolsmsService coolsmsService;

  // coolSMS 테스트 화면
  @GetMapping("/sms")
  public String mySms() {
    return "order/sms";
  }


}
