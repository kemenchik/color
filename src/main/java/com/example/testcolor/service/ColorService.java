package com.example.testcolor.service;

import com.example.testcolor.dto.request.ChangeColorRequest;
import com.example.testcolor.dto.request.CreateColorRequest;
import com.example.testcolor.mapper.ColorMapper;
import com.example.testcolor.model.Color;
import com.example.testcolor.repo.ColorRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColorService {

  private final ColorRepository colorRepository;
  private final ColorMapper colorMapper;
  private final PaletteService paletteService;

  public List<Color> getAll(String login, UUID paletteId) {
    return colorRepository.findByPaletteIdAndPaletteOwnerLogin(paletteId, login);
  }

  public Color create(CreateColorRequest request, String login, UUID paletteId) {
    var palette = paletteService.getById(paletteId, login);

    var color = colorMapper.requestToModel(request, palette);
    return colorRepository.save(color);
  }

  public Color getById(UUID id, String login) {
    return getByIdAndUser(id, login);
  }

  @Transactional
  public void deleteById(UUID id, String login) {
    var model = getByIdAndUser(id, login);
    colorRepository.deleteById(model.getId());
  }


  @Transactional
  public Color changeById(UUID id, String login, ChangeColorRequest request) {
    var model = getByIdAndUser(id, login);
    colorMapper.merge(model, request);

    return colorRepository.save(model);
  }

  private Color getByIdAndUser(UUID id, String login) {
    var colorOpt = colorRepository.findByIdAndPaletteOwnerLogin(id, login);

    if (colorOpt.isEmpty()) {
      throw new IllegalArgumentException("Не найдено цвета с id " + id + " у пользователя " + login);
    }

    return colorOpt.get();
  }
}
