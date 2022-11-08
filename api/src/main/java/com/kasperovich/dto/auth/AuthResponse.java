package com.kasperovich.dto.auth;

import com.kasperovich.enums.Roles;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthResponse {

    String token;

    String login;

    String email;

    Roles roleName;

}
