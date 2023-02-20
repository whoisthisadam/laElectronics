package com.kasperovich.controller;

import com.kasperovich.dto.auth.AuthRequest;
import com.kasperovich.dto.auth.AuthResponse;
import com.kasperovich.security.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Authentication")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final AuthService authService;




    @Operation(
            summary = "Login user in system",
            description = "Return Auth-Token with user login",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful authorization",
                            content = @Content),
                    @ApiResponse(responseCode = "400", description = "Request error", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
            })
    @PostMapping
    public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest request) {
        String accessToken = authService.login(request);

        return ResponseEntity.ok(new AuthResponse(accessToken));
    }
}
