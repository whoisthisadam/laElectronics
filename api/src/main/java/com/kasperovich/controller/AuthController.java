package com.kasperovich.controller;

import com.kasperovich.dto.auth.AuthRequest;
import com.kasperovich.dto.auth.AuthResponse;
import com.kasperovich.security2.jwt.JwtTokenHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    private final JwtTokenHelper tokenUtils;

    private final UserDetailsService userProvider;

    //    @ApiOperation(value = "Login user in system", notes = "Return Auth-Token with user login")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Successful authorization"),
//            @ApiResponse(code = 400, message = "Request error"),
//            @ApiResponse(code = 500, message = "Server error")
//    })
    @PostMapping
    public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest request) {

        /*Check login and password*/
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmailOrLogin(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        /*Generate token with answer to user*/
        return ResponseEntity.ok(
                AuthResponse
                        .builder()
                        .userNameOrEmail(request.getEmailOrLogin())
                        .token(tokenUtils.generateToken(userProvider.loadUserByUsername(request.getEmailOrLogin())))
                        .build()
        );
    }



































//    private final AuthenticationManager authenticationManager;
//
//    private final AuthService authService;
//
//
//
//
//    @Operation(
//            summary = "Login user in system",
//            description = "Return Auth-Token with user login",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "Successful authorization",
//                            content = @Content),
//                    @ApiResponse(responseCode = "400", description = "Request error", content = @Content),
//                    @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
//            })
//    @PostMapping
//    public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest request) {
//        String accessToken = authService.login(request);
//
//        return ResponseEntity.ok(new AuthResponse(accessToken));
//    }
}
