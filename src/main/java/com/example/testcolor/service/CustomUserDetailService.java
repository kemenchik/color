package com.example.testcolor.service;

import com.example.testcolor.model.User;
import com.example.testcolor.repo.UserRepository;
import java.util.Collection;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

  private final UserRepository userRepo;

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    return userRepo.findByLogin(login)
        .map(CustomUserDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException("Пользователь с login`ом " + login + " не найден!"));
  }

  private static class CustomUserDetails implements UserDetails {

    private final String userName;
    private final String password;

    public CustomUserDetails(User user) {
      this.userName = user.getLogin();
      this.password = user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return Collections.emptyList();
    }

    @Override
    public String getPassword() {
      return password;
    }

    @Override
    public String getUsername() {
      return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
      return true;
    }

    @Override
    public boolean isAccountNonLocked() {
      return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }

    @Override
    public boolean isEnabled() {
      return true;
    }
  }
}