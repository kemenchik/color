package com.example.testcolor.dto;

import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaletteDto {

  private UUID id;
  private UserDto owner;
  private String title;
  private List<ColorDto> colors;
}
