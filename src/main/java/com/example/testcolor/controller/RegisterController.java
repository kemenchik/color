package com.example.testcolor.controller;

import com.example.testcolor.dto.request.RegisterRequest;
import com.example.testcolor.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {

  private final RegisterService registerService;

  @PostMapping("/register")
  public ResponseEntity<Void> register(@RequestBody RegisterRequest registerRequest) {
    var login = registerRequest.getLogin();
    var password = registerRequest.getPassword();

    registerService.register(login, password);

    return ResponseEntity.ok().build();
  }
}
