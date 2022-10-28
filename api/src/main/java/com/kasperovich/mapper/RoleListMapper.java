package com.kasperovich.mapper;

import com.kasperovich.controller.requests.RoleRequest;
import com.kasperovich.controller.responses.RoleResponse;
import com.kasperovich.models.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface RoleListMapper {

    public List<Role>toEntityList(List<RoleRequest> roleRequestList);

    public List<RoleResponse>toResponsesList(List<Role>roles);

}
