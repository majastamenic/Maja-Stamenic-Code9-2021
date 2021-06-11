package com.code9.usermicroservice.admin.repository;

import com.code9.usermicroservice.admin.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Admin, Long> { }
