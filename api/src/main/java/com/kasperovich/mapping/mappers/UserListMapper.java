package com.kasperovich.mapping.mappers;


import com.kasperovich.dto.users.UserCreateDto;
import com.kasperovich.dto.users.UserGetDto;
import com.kasperovich.models.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface UserListMapper {

    public List<UserGetDto> toDtoList(List<User>userList);

    public List<User>toEntityList(List<UserCreateDto>dtoList);

}
