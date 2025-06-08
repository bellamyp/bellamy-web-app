package com.bellamyphan.spring_backend.dbuser.service;

import com.bellamyphan.spring_backend.dbuser.entity.Role;
import com.bellamyphan.spring_backend.dbuser.entity.User;
import com.bellamyphan.spring_backend.dbuser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    // private final BCryptPasswordEncoder bCryptPasswordEncoder;
    // private final RoleService roleService;

    // private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Value("${default.admin.username}")
    private String defaultAdminUsername;
    @Value("${default.admin.password}")
    private String defaultAdminPassword;
    @Value("${default.admin.firstname}")
    private String defaultAdminFirstname;
    @Value("${default.admin.lastname}")
    private String defaultAdminLastname;

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
