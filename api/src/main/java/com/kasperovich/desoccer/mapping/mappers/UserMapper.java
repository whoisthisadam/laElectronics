package com.kasperovich.desoccer.mapping.mappers;

import com.kasperovich.desoccer.dto.users.UserCreateDto;
import com.kasperovich.desoccer.dto.users.UserGetDto;
import com.kasperovich.desoccer.models.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {

    UserGetDto toDto(User user);

    User toEntity(UserCreateDto userCreateDto);

}

