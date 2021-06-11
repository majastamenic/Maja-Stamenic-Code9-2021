package com.code9.usermicroservice.user;

import com.code9.usermicroservice.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> { }
