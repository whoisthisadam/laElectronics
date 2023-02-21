package com.kasperovich.desoccer.repository;

import com.kasperovich.desoccer.models.Role;
import com.kasperovich.desoccer.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findAllByName(Roles name);

}
