package com.example.practicingJwt.application.repository;

import com.example.practicingJwt.application.authentication.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Long, Role> {
    Optional<Role> findByName(String name);
}
