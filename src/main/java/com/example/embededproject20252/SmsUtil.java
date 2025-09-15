package com.example.embededproject20252;

import java.util.HashMap;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@PropertySource("classpath:application.properties")
public class SmsUtil {

  private static final Logger log = LoggerFactory.getLogger(SmsUtil.class);

  @Value("${sms.phoneNumber}")
  private String FROM_NUMBER;

  @Value("${api.key}")
  private String API_KEY;

  @Value("${api.secretKey}")
  private String API_SECRET_KEY;

  private static final int MAX_SMS_LENGTH = 45;

  public boolean sendOneMessage(String to, String userName) {
    if (to == null || to.isEmpty()) {
      log.warn("SMS 전송 실패: 받는 번호가 없습니다.");
      return false;
    }
    if (userName == null || userName.isEmpty()) {
      userName = "사용자"; // 기본값
    }

    final String rawMessage = "[약꼭] " + userName + "님 복용 완료!";
    // 45자 초과 시 자르기
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
      return true;
    } catch (CoolsmsException e) {
      log.error("SMS 전송 실패: to={}, message={}", to, messageText, e);
      return false;
    }
  }
}
