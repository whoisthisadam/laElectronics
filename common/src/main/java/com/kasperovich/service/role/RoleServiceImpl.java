package com.kasperovich.service.role;

import com.kasperovich.enums.Roles;
import com.kasperovich.models.Role;
import com.kasperovich.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findRoleByName(Roles name) {
        return roleRepository.findAllByName(name);
    }
}
