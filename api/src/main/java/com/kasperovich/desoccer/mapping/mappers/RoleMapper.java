package com.kasperovich.desoccer.mapping.mappers;

import com.kasperovich.desoccer.dto.roles.RoleCreateDto;
import com.kasperovich.desoccer.dto.roles.RoleGetDto;
import com.kasperovich.desoccer.models.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleGetDto toResponse(Role role);

    Role toEntity(RoleCreateDto roleRequest);

}
