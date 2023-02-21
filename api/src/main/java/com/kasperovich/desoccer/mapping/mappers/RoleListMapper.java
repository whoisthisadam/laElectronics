package com.kasperovich.desoccer.mapping.mappers;

import com.kasperovich.desoccer.dto.roles.RoleCreateDto;
import com.kasperovich.desoccer.dto.roles.RoleGetDto;
import com.kasperovich.desoccer.models.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface RoleListMapper {

    List<Role>toEntityList(List<RoleCreateDto> roleRequestList);

    List<RoleGetDto>toResponsesList(List<Role>roleList);

}
