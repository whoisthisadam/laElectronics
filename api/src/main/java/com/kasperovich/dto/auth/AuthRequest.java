package com.kasperovich.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthRequest {

    @Schema(defaultValue = "arinakasperrr", type = "string", description = "User Login")
    @NotBlank
    private String login;

    @Schema(defaultValue = "arinak2006", type = "string", description = "User password")
    @NotBlank
    private String password;

}
