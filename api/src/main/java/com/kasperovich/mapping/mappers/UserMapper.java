package com.kasperovich.mapping.mappers;

import com.kasperovich.dto.users.UserCreateDto;
import com.kasperovich.dto.users.UserGetDto;
import com.kasperovich.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public UserGetDto toDto(User user);

    public User toEntity(UserCreateDto userCreateDto);

}
