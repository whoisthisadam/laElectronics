package com.kasperovich.controller;

import com.kasperovich.dto.users.UserCreateDto;
import com.kasperovich.dto.users.UserGetDto;
import com.kasperovich.mapper.UserListMapper;
import com.kasperovich.mapper.UserMapper;
import com.kasperovich.models.Credentials;
import com.kasperovich.models.Edit;
import com.kasperovich.models.User;
import com.kasperovich.repository.RoleRepository;
import com.kasperovich.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@Validated
@Slf4j
@RequestMapping("data/users")
@RequiredArgsConstructor
@Api(tags = {"Users"})
public class UserController {

    private final UserRepository userRepository;

    private final UserListMapper userListMapper;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    @GetMapping
    @ApiOperation(value = "Return all users")
    public ResponseEntity<List<UserGetDto>>findAll(){
        return ResponseEntity.ok(userListMapper.toDtoList(userRepository.findAll()));
    }

    @PostMapping
    @ApiOperation(value = "Create a new user")
    public ResponseEntity<User>createUser(@Valid @RequestBody UserCreateDto userCreateDto){
        User user=userMapper.toEntity(userCreateDto);
        user.setRole(roleRepository.findById(userCreateDto.getRoleId()).get());
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }


}
