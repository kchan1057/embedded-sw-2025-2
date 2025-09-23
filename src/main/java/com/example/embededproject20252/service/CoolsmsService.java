package com.example.embededproject20252.service;

import java.util.HashMap;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class CoolsmsService {

  private static final Logger log = LoggerFactory.getLogger(CoolsmsService.class);

  @Value("${sms.phoneNumber}")
  private String FROM_NUMBER;

  @Value("${api.key}")
  private String API_KEY;

  @Value("${api.secretKey}")
  private String API_SECRET_KEY;

  private static final int MAX_SMS_LENGTH = 45;

  public void sendOneMessage(String to, String userName, int motion) {
    if (to == null || to.isEmpty()) {
      log.warn("SMS 전송 실패: 받는 번호가 없습니다.");
      return;
    }
    if (userName == null || userName.isEmpty()) {
      userName = "사용자";
    }

    final String rawMessage = String.format("[약꼭] %s님 %d번 약통 복용 완료!", userName, motion);
    final String messageText = rawMessage.length() > MAX_SMS_LENGTH
        ? rawMessage.substring(0, MAX_SMS_LENGTH)
        : rawMessage;

    try {
      final Message coolsms = new Message(API_KEY, API_SECRET_KEY);
      final HashMap<String, String> params = new HashMap<>();
      params.put("to", to);
      params.put("from", FROM_NUMBER);
      params.put("type", "sms");
      params.put("text", messageText);

      coolsms.send(params);

      log.info("SMS 전송 성공: to={}, message={}", to, messageText);
      return;
    } catch (CoolsmsException e) {
      log.error("SMS 전송 실패: to={}, message={}", to, messageText, e);
      return;
    }
  }
}