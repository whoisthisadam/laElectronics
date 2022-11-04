package com.kasperovich.controller;

import com.kasperovich.dto.users.UserGetDto;
import com.kasperovich.mapper.UserListMapper;
import com.kasperovich.mapper.UserMapper;
import com.kasperovich.repository.RoleRepository;
import com.kasperovich.repository.UserRepository;
import com.kasperovich.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@Slf4j
@RequestMapping("data/users")
@RequiredArgsConstructor
@Tag(name = "Users")
public class UserController {

    private final UserRepository userRepository;

    private final UserListMapper userListMapper;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    private final UserService userService;

    @Operation(
    summary = "Gets all users",
    responses = {
        @ApiResponse(
                responseCode = "200",
                description = "Found the users",
                content =
                @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation =UserGetDto.class))))
    },parameters = {
        @Parameter(
                in = ParameterIn.HEADER,
                name = "X-Auth-Token",
                required = true,
                description = "JWT Token, can be generated in auth controller /auth")
    })
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserGetDto>>findAll(){
        return ResponseEntity.ok(userListMapper.toDtoList(userRepository.findAll()));
    }



}
