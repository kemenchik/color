package com.example.testcolor.mapper;

import com.example.testcolor.dto.UserDto;
import com.example.testcolor.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDto modelToDto(User model);
}
