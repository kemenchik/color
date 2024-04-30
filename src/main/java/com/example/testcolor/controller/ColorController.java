package com.example.testcolor.controller;

import com.example.testcolor.dto.ColorDto;
import com.example.testcolor.dto.request.ChangeColorRequest;
import com.example.testcolor.dto.request.CreateColorRequest;
import com.example.testcolor.mapper.ColorMapper;
import com.example.testcolor.service.ColorService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/color")
@RequiredArgsConstructor
public class ColorController {

  private final ColorService colorService;
  private final ColorMapper colorMapper;

  @GetMapping("/palette/{paletteId}")
  public ResponseEntity<List<ColorDto>> getAll(@AuthenticationPrincipal UserDetails userDetails, @PathVariable UUID paletteId) {
    var login = userDetails.getUsername();

    var models = colorService.getAll(login, paletteId);
    var dtos = colorMapper.modelToDto(models);

    return ResponseEntity.ok(dtos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ColorDto> getById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable UUID id) {
    var login = userDetails.getUsername();

    var models = colorService.getById(id, login);
    var dtos = colorMapper.modelToDto(models);

    return ResponseEntity.ok(dtos);
  }

  @PostMapping("/palette/{paletteId}")
  public ResponseEntity<ColorDto> create(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CreateColorRequest request,
      @PathVariable UUID paletteId) {
    var login = userDetails.getUsername();

    var model = colorService.create(request, login, paletteId);
    var dto = colorMapper.modelToDto(model);

    return ResponseEntity.ok(dto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ColorDto> delete(@AuthenticationPrincipal UserDetails userDetails,
      @PathVariable UUID id) {
    var login = userDetails.getUsername();

    colorService.deleteById(id, login);

    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<ColorDto> change(@AuthenticationPrincipal UserDetails userDetails, @PathVariable UUID id,
      @RequestBody ChangeColorRequest request) {
    var login = userDetails.getUsername();

    var model = colorService.changeById(id, login, request);
    var dto = colorMapper.modelToDto(model);

    return ResponseEntity.ok(dto);
  }
}
