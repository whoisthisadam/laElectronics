package com.kasperovich.dto.auth;

import com.kasperovich.enums.Roles;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthResponse {

    String token;

    final String type="Bearer";

    public AuthResponse(String token) {
        this.token = token;
    }
    //     String login;
//
//    String email;
//
//    Roles roleName;

}
