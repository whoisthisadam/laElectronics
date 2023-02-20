package com.kasperovich.dto.auth;

import com.kasperovich.enums.Roles;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthResponse {

    String userNameOrEmail;
    String token;

    final String type="Bearer";

    //     String login;
//
//    String email;
//
//    Roles roleName;

}
