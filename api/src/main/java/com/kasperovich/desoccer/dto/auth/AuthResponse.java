package com.kasperovich.desoccer.dto.auth;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthResponse {

    String userNameOrEmail;
    String token;

}
