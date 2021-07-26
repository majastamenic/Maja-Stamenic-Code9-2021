package com.code9.usermicroservice.user.service.impl;

import com.code9.usermicroservice.exception.NotFoundException;
import com.code9.usermicroservice.user.domain.Role;
import com.code9.usermicroservice.user.repository.RoleRepository;
import com.code9.usermicroservice.user.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public Role findByName(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null)
            throw new NotFoundException("There is no role with name: " + name);
        return role;
    }
}
