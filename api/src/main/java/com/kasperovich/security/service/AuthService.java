package com.kasperovich.security.service;

import com.kasperovich.dto.auth.AuthRequest;
import com.kasperovich.dto.auth.AuthResponse;

public interface AuthService {

    String login(AuthRequest request);

}
