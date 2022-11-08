package com.kasperovich.repository;

import com.kasperovich.enums.Roles;
import com.kasperovich.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findAllByName(Roles name);

}
