package com.kasperovich.service.role;

import com.kasperovich.enums.Roles;
import com.kasperovich.models.Role;

public interface RoleService {

    public Role findRoleByName(Roles name);

}
