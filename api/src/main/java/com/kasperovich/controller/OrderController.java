package com.kasperovich.controller;

import com.kasperovich.dto.order.OrderCreateDto;
import com.kasperovich.dto.order.OrderGetDto;
import com.kasperovich.dto.roles.RoleGetDto;
import com.kasperovich.mapping.converters.order.OrderCreateConverter;
import com.kasperovich.mapping.converters.order.OrderGetConverter;
import com.kasperovich.models.Order;
import com.kasperovich.service.order.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@Validated
@Slf4j
@RequestMapping("data/orders")
@RequiredArgsConstructor
@Tag(name = "Orders")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {

    OrderService orderService;

    OrderCreateConverter orderCreateConverter;

    OrderGetConverter orderGetConverter;


    @Operation(
            summary = "Create role(Admin only)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Created",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = RoleGetDto.class)))
                            })
            }
    )
    @PostMapping
    public ResponseEntity<Map<String, OrderGetDto>>create(@RequestBody OrderCreateDto orderCreateDto){
        Order order=orderCreateConverter.convert(orderCreateDto);
        orderService.createOrder(order);
        return new ResponseEntity<>(Collections.singletonMap("New order:", orderGetConverter.convert(order)), HttpStatus.CREATED);
    }


}
