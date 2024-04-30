package com.example.testcolor.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class UserDto {

  private UUID id;
  private String login;
}
