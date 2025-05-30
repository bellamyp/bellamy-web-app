package com.bellamyphan.spring_backend.dbuser.repository;

import com.bellamyphan.spring_backend.dbuser.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // JpaRepository provides basic CRUD operations

    Optional<Role> findByName(String name);
}