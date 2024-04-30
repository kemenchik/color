package com.example.testcolor.service;

import com.example.testcolor.dto.request.ChangePaletteRequest;
import com.example.testcolor.dto.request.CreatePaletteRequest;
import com.example.testcolor.mapper.PaletteMapper;
import com.example.testcolor.model.Palette;
import com.example.testcolor.repo.PaletteRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaletteService {

  private final PaletteRepository paletteRepository;
  private final UserService userService;
  private final PaletteMapper paletteMapper;

  public List<Palette> getAll(String login) {
    return paletteRepository.findByOwnerLogin(login);
  }

  public Palette create(CreatePaletteRequest request, String login) {
    var userOpt = userService.findByLogin(login);

    if (userOpt.isEmpty()) {
      throw new IllegalArgumentException("Пользователь с login`ом " + login + " не найден");
    }

    var palette = paletteMapper.requestToModel(request, userOpt.get());
    return paletteRepository.save(palette);
  }

  public Palette getById(UUID id, String login) {
    return getByIdAndUser(id, login);
  }

  @Transactional
  public void deleteById(UUID id, String login) {
    var model = getByIdAndUser(id, login);
    paletteRepository.deleteById(model.getId());
  }


  @Transactional
  public Palette changeById(UUID id, String login, ChangePaletteRequest request) {
    var model = getByIdAndUser(id, login);
    paletteMapper.merge(model, request);

    return paletteRepository.save(model);
  }

  private Palette getByIdAndUser(UUID id, String login) {
    var paletteOpt = paletteRepository.findByIdAndOwnerLogin(id, login);

    if (paletteOpt.isEmpty()) {
      throw new IllegalArgumentException("Не найдено палитры с id " + id + " у пользователя " + login);
    }

    return paletteOpt.get();
  }
}
