package com.example.testcolor.mapper;

import com.example.testcolor.dto.PaletteDto;
import com.example.testcolor.dto.request.ChangePaletteRequest;
import com.example.testcolor.dto.request.CreatePaletteRequest;
import com.example.testcolor.model.Palette;
import com.example.testcolor.model.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ColorMapper.class})
public interface PaletteMapper {

  PaletteDto modelToDto(Palette model);

  List<PaletteDto> modelToDto(List<Palette> model);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "owner", ignore = true)
  @Mapping(target = "colors", ignore = true)
  void merge(@MappingTarget Palette model, ChangePaletteRequest request);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "owner", source = "user")
  @Mapping(target = "colors", ignore = true)
  Palette requestToModel(CreatePaletteRequest request, User user);
}
