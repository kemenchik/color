package com.example.testcolor.controller;

import com.example.testcolor.dto.request.LoginRequest;
import com.example.testcolor.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

  private final LoginService loginService;

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
    var login = loginRequest.getLogin();
    var password = loginRequest.getPassword();

    var token = loginService.login(login, password);

    return ResponseEntity.ok(token);
  }
}
