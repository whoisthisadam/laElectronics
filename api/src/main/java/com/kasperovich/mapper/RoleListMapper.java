package com.kasperovich.mapper;

import com.kasperovich.dto.roles.RoleCreateDto;
import com.kasperovich.dto.roles.RoleGetDto;
import com.kasperovich.models.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface RoleListMapper {

    public List<Role>toEntityList(List<RoleCreateDto> roleRequestList);

    public List<RoleGetDto>toResponsesList(List<Role>roleList);

}
