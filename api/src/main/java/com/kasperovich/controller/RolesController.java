package com.kasperovich.controller;

import com.kasperovich.controller.responses.RoleResponse;
import com.kasperovich.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kasperovich.repository.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Validated
@Slf4j
@RequestMapping("/rest/data/roles")
@RequiredArgsConstructor
public class RolesController {


    private final RoleRepository roleRepository;

    @GetMapping
    public ResponseEntity<List<RoleResponse>>findAll(){
        List<RoleResponse>roleResponseList=roleRepository.findAll()
                .stream()
                .map(x->{
            return new RoleResponse(x.getId(), x.getName(), Optional.of
                    (x.getUsers().stream().map(User::getId).collect(Collectors.toSet())).orElse(null));
        }).collect(Collectors.toList());
        return ResponseEntity.ok(roleResponseList);
    }
}
