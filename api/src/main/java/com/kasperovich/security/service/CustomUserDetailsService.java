package com.kasperovich.security.service;

import com.kasperovich.dto.auth.AuthResponse;
import com.kasperovich.models.Role;
import com.kasperovich.models.User;
import com.kasperovich.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException{
        // Let people login with either username or email
        User user = userRepository.findByCredentialsLoginOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email:" + usernameOrEmail));

        Set<Role>role =new HashSet<>(Collections.singletonList(user.getRole()));
        // return a new user with the username, password, and authorities
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getCredentials().getPassword(), mapRolesToAuthorities(role));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        // convert the roles to a list of authorities
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName().toString())).collect(Collectors.toList());
    }



}
