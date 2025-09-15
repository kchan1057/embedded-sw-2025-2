package com.example.embededproject20252;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class ContactController {

  private final SmsUtil smsUtil;


  // coolSMS 테스트 화면
  @GetMapping("/sms")
  public String mySms() {
    return "order/sms";
  }

  // coolSMS 구현 로직 연결
  @GetMapping("/check/sendSMS")
  public @ResponseBody boolean sendSMS(
      @RequestParam(value="to") String to,
      @RequestParam(value="user_name") String userName
  ) throws CoolsmsException {
    return smsUtil.sendOneMessage(to, userName);
  }
}
