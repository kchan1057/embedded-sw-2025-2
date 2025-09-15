package com.example.embededproject20252;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/register")
  public boolean registerUser(@RequestParam String name,
      @RequestParam String phoneNumber) {
    return userService.registerUser(name, phoneNumber);
  }
}
