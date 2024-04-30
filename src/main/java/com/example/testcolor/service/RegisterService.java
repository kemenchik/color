package com.example.testcolor.service;

import com.example.testcolor.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  public void register(String login, String password) {
    if (password.isEmpty()) {
      throw new IllegalArgumentException("Пароль должен быть не пустым");
    }

    if (userService.findByLogin(login).isPresent()) {
      throw new IllegalArgumentException("Пользователь с login`ом " + login + " уже зарегистрирован");
    }

    User user = new User();

    user.setLogin(login);
    user.setPassword(passwordEncoder.encode(password));

    userService.save(user);
  }
}
