package com.code9.usermicroservice.user.service;

import com.code9.usermicroservice.user.domain.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role findByName(String name);
}
