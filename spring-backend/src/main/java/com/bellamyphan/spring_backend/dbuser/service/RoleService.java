package com.bellamyphan.spring_backend.dbuser.service;

import com.bellamyphan.spring_backend.dbuser.entity.Role;
import com.bellamyphan.spring_backend.dbuser.entity.RoleEnum;
import com.bellamyphan.spring_backend.dbuser.exception.MissingRoleException;
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

    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);
    private final RoleRepository roleRepository;

    @Transactional
    public void initializeRoles() {
        try {
            for (RoleEnum roleEnum : RoleEnum.values()) {
                String roleName = roleEnum.getRoleName(); // e.g. "ROLE_USER", "ROLE_ADMIN"
                String displayName = roleEnum.getDisplayName(); // e.g. "User", "Admin"
                createRoleIfNotExists(roleName, displayName);
            }
        } catch (DataAccessException dae) {
            logger.error("Database error while creating roles", dae);
            throw new RoleCreationException("Database error while creating roles", dae);
        } catch (Exception ex) {
            logger.error("Unexpected error while creating roles", ex);
            throw new RoleCreationException("Unexpected error while creating roles", ex);
        }
    }

    public Role getRoleOrThrow(String roleName) {
        RoleEnum roleEnum = RoleEnum.fromRoleName(roleName);
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> {
                    logger.error("Missing required role: {}", roleEnum.getDisplayName());
                    return new MissingRoleException("Role not found: " + roleEnum.getDisplayName());
                });
    }

    private void createRoleIfNotExists(String roleName, String displayName) {
        roleRepository.findByName(roleName)
                .map(existingRole -> {
                    logger.info("Role '{}' already exists", displayName);
                    return existingRole;
                })
                .orElseGet(() -> {
                    Role role = new Role(roleName);
                    roleRepository.save(role);
                    logger.info("Created role: {}", displayName);
                    return role;
                });
    }
}
