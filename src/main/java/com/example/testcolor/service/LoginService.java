package com.example.testcolor.service;

import com.example.testcolor.config.security.JwtProvider;
import javax.security.auth.login.LoginException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

  private final JwtProvider jwtProvider;
  private final CustomUserDetailService userDetailService;
  private final PasswordEncoder passwordEncoder;

  @SneakyThrows
  public String login(String login, String password) {
    UserDetails userDetails = userDetailService.loadUserByUsername(login);

    if (passwordEncoder.matches(password, userDetails.getPassword())) {
      return jwtProvider.generateToken(login);
    }

    throw new LoginException("Произошла ошибка при валидации пользователя");
  }
}
