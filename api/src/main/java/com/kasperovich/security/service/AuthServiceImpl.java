package com.kasperovich.security.service;

import com.kasperovich.dto.auth.AuthRequest;
import com.kasperovich.dto.auth.AuthResponse;
import com.kasperovich.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public String login(AuthRequest request) {
        // Authenticate user with username or email and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmailOrLogin(), request.getPassword()
                ));

        // Set authentication in context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateJwtToken(authentication);
    }
}
