package com.kasperovich.controller;

import com.kasperovich.dto.roles.RoleGetDto;
import com.kasperovich.mapping.mappers.RoleListMapper;
import com.kasperovich.service.role.RoleService;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.kasperovich.repository.RoleRepository;

import java.util.List;

@RestController
@Validated
@Slf4j
@RequestMapping("data/roles")
@RequiredArgsConstructor
@Tag(name = "Roles")
public class RolesController {



    private final RoleService roleService;

    private final RoleListMapper roleListMapper;


    @Operation(
            summary = "Gets all roles(Admin&Moderator only)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found the roles",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = RoleGetDto.class)))
                            })
            }
            ,parameters = {
                    @Parameter(
                            in = ParameterIn.HEADER,
                            name = "X-Auth-Token",
                            required = true,
                            description = "JWT Token, can be generated in auth controller /auth")
            })
    @Secured({"ROLE_ADMIN","ROLE_MODERATOR"})
    @GetMapping
    public ResponseEntity<List<RoleGetDto>>findAll(){
        List<RoleGetDto>roleResponseList=roleListMapper.toResponsesList(roleService.findAll());
        return ResponseEntity.ok(roleResponseList);
    }

    @Operation(
            summary = "Delete role by id(Admin only)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Deleted",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = RoleGetDto.class)))
                            })
            }
            ,parameters = {
            @Parameter(
                    in = ParameterIn.HEADER,
                    name = "X-Auth-Token",
                    required = true,
                    description = "JWT Token, can be generated in auth controller /auth")
    })
    @Secured("ROLE_ADMIN")
    @DeleteMapping
    public ResponseEntity<String>deleteById(@RequestParam String id){
        return new ResponseEntity<>("Role with ID number "+roleService.deleteById(Long.parseLong(id))+" deleted",
                HttpStatus.OK);
    }
}
