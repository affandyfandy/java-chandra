package com.assignment1.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment1.security.entity.Role;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(String name);
}
