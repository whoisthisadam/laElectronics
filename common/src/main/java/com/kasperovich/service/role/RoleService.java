package com.kasperovich.service.role;

import com.kasperovich.enums.Roles;
import com.kasperovich.models.Role;

import java.util.List;

public interface RoleService {

    Role findRoleByName(Roles name);

    List<Role> findAll();

    Long deleteById(Long id);

    Role createRole(Role role);

}
