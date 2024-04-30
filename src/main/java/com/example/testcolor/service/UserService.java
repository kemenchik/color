package com.example.testcolor.service;

import com.example.testcolor.model.User;
import com.example.testcolor.repo.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public Optional<User> findByLogin(String login) {
    return userRepository.findByLogin(login);
  }

  public User save(User user) {
    return userRepository.save(user);
  }
}
