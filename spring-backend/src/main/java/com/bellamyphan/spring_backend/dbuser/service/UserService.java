package com.bellamyphan.spring_backend.dbuser.service;

import com.bellamyphan.spring_backend.dbuser.entity.Role;
import com.bellamyphan.spring_backend.dbuser.entity.RoleEnum;
import com.bellamyphan.spring_backend.dbuser.entity.User;
import com.bellamyphan.spring_backend.dbuser.exception.UserAlreadyExistsException;
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
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
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
    public void createFirstAdminUser() {
        // Get the default admin username from properties
        User adminUser = new User(
                defaultAdminFirstname, defaultAdminLastname, defaultAdminUsername, defaultAdminPassword);
        // Save the admin user
        saveUser(adminUser, RoleEnum.ROLE_ADMIN);
    }

    @Transactional
    public void createFirstDemoUser() {
        // Default demo user details
        String firstName = "Test First Name";
        String lastName = "Test Last Name";
        String username = "test";
        String password = "test";
        // Create the demo user and save it
        User demoUser = new User(firstName, lastName, username, password);
        saveUser(demoUser, RoleEnum.ROLE_DEMO);
    }

    @Transactional
    public User saveUser(User user, RoleEnum userRoleEnum) {
        // Check if the user already exists by username
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(
                    "This username " + user.getUsername() + " already exists. Skipping creation.");
        }
        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Set the roles for the user
        Role userRole = roleService.getRoleOrThrow(userRoleEnum.getRoleName());
        user.setRoles(new HashSet<>(List.of(userRole)));
        // Save the user to the database
        logger.info("Saving user with username: {}", user.getUsername());
        return userRepository.save(user);
    }

    public User findByUserName(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find the user with username: " + username));
    }

    public User findByUsernameWithRoles(String username) {
        // Retrieve user from the database using username
        return userRepository.findByUsernameWithRoles(username)
                .orElseThrow(() -> {
                    logger.warn("User not found with username: {}", username);
                    return new UsernameNotFoundException("User not found with username: " + username);
                });
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
