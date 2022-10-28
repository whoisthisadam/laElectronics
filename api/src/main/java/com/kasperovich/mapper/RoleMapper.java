package com.kasperovich.mapper;

import com.kasperovich.controller.requests.RoleRequest;
import com.kasperovich.controller.responses.RoleResponse;
import com.kasperovich.models.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    public RoleResponse toResponse(Role role);

    public Role toEntity(RoleRequest roleRequest);

}
