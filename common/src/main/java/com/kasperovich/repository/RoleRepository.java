package com.kasperovich.repository;

import com.kasperovich.enums.Roles;
import com.kasperovich.models.Role;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.Size;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findAllByName(@Size(max = 40) Roles name);

}
