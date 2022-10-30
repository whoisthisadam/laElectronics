package com.kasperovich.mapper;

import com.kasperovich.dto.users.UserCreateDto;
import com.kasperovich.dto.users.UserGetDto;
import com.kasperovich.models.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public UserGetDto toDto(User user);

    public User toEntity(UserCreateDto userCreateDto);

}

