package com.kasperovich.controller;

import com.kasperovich.dto.users.UserCreateDto;
import com.kasperovich.dto.users.UserGetDto;
import com.kasperovich.enums.Roles;
import com.kasperovich.mapper.UserMapper;
import com.kasperovich.models.User;
import com.kasperovich.repository.RoleRepository;
import com.kasperovich.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@Validated
@Slf4j
@RequestMapping("data/registration")
@RequiredArgsConstructor
@Tag(name = "Registration")
public class RegistrationController {

    private final UserService userService;

    private final UserMapper userMapper;

    private final RoleRepository roleRepository;

    @Operation(
            summary = "Register a new User",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "User registered successfully",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserGetDto.class))),
                    @ApiResponse(
                            responseCode = "409",
                            description = "User not registered, Conflict",
                            content = @Content),
                    @ApiResponse(
                            responseCode = "500",
                            description = "User not registered, Illegal Arguments",
                            content = @Content)
            })
    @PostMapping
    @Transactional
    public ResponseEntity<Map<String, UserGetDto>> addUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        User user = userMapper.toEntity(userCreateDto);
        if(user.getCredentials()==null){
            user.setRole(roleRepository.findAllByName(Roles.USER_NOT_AUTHORIZED));
        }
        else{
            user.setRole(roleRepository.findAllByName(Roles.USER_AUTHORIZED));
        }
        userService.createUser(user);
        return new ResponseEntity<>(Collections.singletonMap("New user:", userMapper.toDto(user)),
                HttpStatus.CREATED);
    }
}