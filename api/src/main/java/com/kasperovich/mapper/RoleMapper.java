package com.kasperovich.mapper;

import com.kasperovich.dto.roles.RoleCreateDto;
import com.kasperovich.dto.roles.RoleGetDto;
import com.kasperovich.models.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    public RoleGetDto toResponse(Role role);

    public Role toEntity(RoleCreateDto roleRequest);

}
