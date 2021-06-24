package com.code9.usermicroservice.user.service;

import com.code9.usermicroservice.user.domain.Role;
import com.code9.usermicroservice.user.exception.NotFoundException;
import com.code9.usermicroservice.user.repository.IRoleRepository;
import com.code9.usermicroservice.user.service.interfaces.IRoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {

    IRoleRepository roleRepository;

    public RoleService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String name) {
        Role role = roleRepository.findByName(name);
        if(role == null)
            throw new NotFoundException("There is no role with name: " + name);
        return role;
    }
}
