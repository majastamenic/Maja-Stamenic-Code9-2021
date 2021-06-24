package com.code9.usermicroservice.user.service.interfaces;

import com.code9.usermicroservice.user.domain.Role;
import org.springframework.stereotype.Service;

@Service
public interface IRoleService {
    Role findByName(String name);
}
