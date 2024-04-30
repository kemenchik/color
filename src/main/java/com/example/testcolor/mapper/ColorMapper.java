package com.example.testcolor.mapper;

import com.example.testcolor.dto.ColorDto;
import com.example.testcolor.dto.request.ChangeColorRequest;
import com.example.testcolor.dto.request.CreateColorRequest;
import com.example.testcolor.model.Color;
import com.example.testcolor.model.Palette;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ColorMapper {

  ColorDto modelToDto(Color model);

  List<ColorDto> modelToDto(List<Color> model);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "palette", ignore = true)
  void merge(@MappingTarget Color model, ChangeColorRequest request);

  @Mapping(target = "id", ignore = true)
  Color requestToModel(CreateColorRequest request, Palette palette);
}
