package com.kasperovich.controller;

import com.kasperovich.dto.users.UserCreateDto;
import com.kasperovich.dto.users.UserGetDto;
import com.kasperovich.mapping.converters.user.UserUpdateConverter;
import com.kasperovich.mapping.mappers.UserListMapper;
import com.kasperovich.mapping.mappers.UserMapper;
import com.kasperovich.models.User;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@Slf4j
@RequestMapping("data/users")
@RequiredArgsConstructor
@Tag(name = "Users")
public class UserController {

    private final UserListMapper userListMapper;

    private final UserService userService;

    private final UserMapper userMapper;

    private final UserUpdateConverter userUpdateConverter;

    @Operation(
    summary = "Gets all users(Admin only)",
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
        return ResponseEntity.ok(userListMapper.toDtoList(userService.findAll()));
    }

    @Operation(
            summary = "Update user(Admin only)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User updated",
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
    @PatchMapping("/update")
    public ResponseEntity<Map<String, UserGetDto>>updateUser(@RequestParam String id, @RequestBody UserCreateDto userCreateDto){
        Long Id=Long.parseLong(id);
        User user= userUpdateConverter.doConvert(userCreateDto, Id);
        return new ResponseEntity<>(
                Collections.singletonMap(
                        "Updated user:", userMapper.toDto(userService.updateUser(user))), HttpStatus.OK
        );
    }

    @Operation(
            summary = "Delete user(Admin only)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User updated",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation =String.class))))
            } ,parameters = {
            @Parameter(
                    in = ParameterIn.HEADER,
                    name = "X-Auth-Token",
                    required = true,
                    description = "JWT Token, can be generated in auth controller /auth")
    })
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PatchMapping("/delete")
    public ResponseEntity<String>deleteUser(@RequestParam String id){
        Long iD=Long.parseLong(id);
        userService.deleteUser(iD);
        return ResponseEntity.ok("Deleted user with ID number "+id);
    }
}
