package com.kasperovich.controller;

import com.kasperovich.controller.responses.RoleResponse;
import com.kasperovich.mapper.RoleListMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kasperovich.repository.RoleRepository;

import java.util.List;

@RestController
@Validated
@Slf4j
@RequestMapping("/rest/data/roles")
@RequiredArgsConstructor
@Api(tags = {"Roles"})
public class RolesController {


    private final RoleRepository roleRepository;

    private final RoleListMapper roleListMapper;

    @GetMapping
    @ApiOperation(value = "Finding all roles")
    public ResponseEntity<List<RoleResponse>>findAll(){
        List<RoleResponse>roleResponseList=roleListMapper.toResponsesList(roleRepository.findAll());
        return ResponseEntity.ok(roleResponseList);
    }
}
