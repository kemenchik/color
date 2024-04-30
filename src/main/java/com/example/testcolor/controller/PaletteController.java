package com.example.testcolor.controller;

import com.example.testcolor.dto.PaletteDto;
import com.example.testcolor.dto.request.ChangePaletteRequest;
import com.example.testcolor.dto.request.CreatePaletteRequest;
import com.example.testcolor.mapper.PaletteMapper;
import com.example.testcolor.service.PaletteService;
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
@RequestMapping("/api/palette")
@RequiredArgsConstructor
public class PaletteController {

  private final PaletteService paletteService;
  private final PaletteMapper paletteMapper;

  @GetMapping
  public ResponseEntity<List<PaletteDto>> getAll(@AuthenticationPrincipal UserDetails userDetails) {
    var login = userDetails.getUsername();

    var models = paletteService.getAll(login);
    var dtos = paletteMapper.modelToDto(models);

    return ResponseEntity.ok(dtos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PaletteDto> getById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable UUID id) {
    var login = userDetails.getUsername();

    var model = paletteService.getById(id, login);
    var dto = paletteMapper.modelToDto(model);

    return ResponseEntity.ok(dto);
  }

  @PostMapping
  public ResponseEntity<PaletteDto> create(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CreatePaletteRequest request) {
    var login = userDetails.getUsername();

    var model = paletteService.create(request, login);
    var dto = paletteMapper.modelToDto(model);

    return ResponseEntity.ok(dto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<PaletteDto> delete(@AuthenticationPrincipal UserDetails userDetails, @PathVariable UUID id) {
    var login = userDetails.getUsername();

    paletteService.deleteById(id, login);

    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<PaletteDto> change(@AuthenticationPrincipal UserDetails userDetails, @PathVariable UUID id,
      @RequestBody ChangePaletteRequest request) {
    var login = userDetails.getUsername();

    var model = paletteService.changeById(id, login, request);
    var dto = paletteMapper.modelToDto(model);

    return ResponseEntity.ok(dto);
  }
}
