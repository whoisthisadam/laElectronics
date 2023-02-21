package com.kasperovich.desoccer.service.role;

import com.kasperovich.desoccer.enums.Roles;
import com.kasperovich.desoccer.models.Role;

import java.util.List;

public interface RoleService {

    Role findRoleByName(Roles name);

    List<Role> findAll();

    Long deleteById(Long id);

    Role createRole(Role role);

}
