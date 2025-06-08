package com.bellamyphan.spring_backend.dbuser.service;

import com.bellamyphan.spring_backend.dbuser.entity.Role;
import com.bellamyphan.spring_backend.dbuser.entity.User;
import com.bellamyphan.spring_backend.dbuser.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;

    @Value("${default.admin.username}")
    private String defaultAdminUsername;
    @Value("${default.admin.password}")
    private String defaultAdminPassword;
    @Value("${default.admin.firstname}")
    private String defaultAdminFirstname;
    @Value("${default.admin.lastname}")
    private String defaultAdminLastname;

    @Transactional
    public void createFirstUser() {
        if (userRepository.findByUsername(defaultAdminUsername).isEmpty()) {
            // Encode the admin password
            String encodedPassword = bCryptPasswordEncoder.encode(defaultAdminPassword);
            logger.debug("Encoded password for admin user: {}", defaultAdminUsername);
            // Check if ROLE_ADMIN, ROLE_USER exist, else throw exceptions
            Role adminRole = roleService.getRoleOrThrow("ROLE_ADMIN");
            Role userRole = roleService.getRoleOrThrow("ROLE_USER");
            // Create the user and assign the role
            User adminUser = new User();
            adminUser.setFirstName(defaultAdminFirstname);
            adminUser.setLastName(defaultAdminLastname);
            adminUser.setUsername(defaultAdminUsername);
            adminUser.setPassword(encodedPassword);
            adminUser.setRoles(new HashSet<>(List.of(adminRole, userRole)));
            // Save the user
            userRepository.save(adminUser);
            logger.info("Admin user created with username: {}", defaultAdminUsername);
        } else {
            logger.info("Admin user with username '{}' already exists. Skipping creation.", defaultAdminUsername);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameWithRoles(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }

    // Convert roles to authorities in the format required by Spring Security
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
