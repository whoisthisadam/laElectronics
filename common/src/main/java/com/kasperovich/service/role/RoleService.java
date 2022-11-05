package com.kasperovich.service.role;

import com.kasperovich.enums.Roles;
import com.kasperovich.models.Role;

import java.util.List;

public interface RoleService {

    public Role findRoleByName(Roles name);

    public List<Role> findAll();

    public Long deleteById(Long id);

}
