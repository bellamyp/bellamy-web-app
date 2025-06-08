package com.bellamyphan.spring_backend.dbuser.service;

import com.bellamyphan.spring_backend.dbuser.entity.Role;
import com.bellamyphan.spring_backend.dbuser.exception.RoleCreationException;
import com.bellamyphan.spring_backend.dbuser.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Transactional
    public void createFirstRole() {
        try {
            // Create or get the default admin role
            Role adminRole = createRoleIfNotExists("ROLE_ADMIN");
            // Create or get the default user role
            Role userRole = createRoleIfNotExists("ROLE_USER");
        } catch (DataAccessException dae) {
            logger.error("Database error while creating roles", dae);
            throw new RoleCreationException("Database error while creating roles", dae);
        } catch (Exception ex) {
            logger.error("Unexpected error while creating roles", ex);
            throw new RoleCreationException("Unexpected error while creating roles", ex);
        }
    }

    private Role createRoleIfNotExists(String roleName) {
        return roleRepository.findByName(roleName)
                .map(existingRole -> {
                    logger.info("Role '{}' already exists", roleName);
                    return existingRole;
                })
                .orElseGet(() -> {
                    Role role = new Role(roleName);
                    roleRepository.save(role);
                    logger.info("Created role: {}", roleName);
                    return role;
                });
    }
}
