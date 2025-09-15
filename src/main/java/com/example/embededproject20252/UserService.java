package com.example.embededproject20252;

import com.example.embededproject20252.entity.User;
import com.example.embededproject20252.UserRepository;
import com.example.embededproject20252.SmsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final SmsUtil smsUtil;

  @Transactional
  public boolean registerUser(String name, String phoneNumber) {
    User user = User.builder()
                    .name(name)
                    .phoneNumber(phoneNumber)
                    .build();

    userRepository.save(user);

    // 등록 즉시 SMS 발송
    return smsUtil.sendOneMessage(phoneNumber, name);
  }
}
