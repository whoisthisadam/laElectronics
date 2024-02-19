package com.kasperovich.laelectronics.api.controller;

import com.kasperovich.laelectronics.api.dto.subscription.DeleteSubscriptionDto;
import com.kasperovich.laelectronics.api.dto.subscription.SubscriptionCreateDto;
import com.kasperovich.laelectronics.api.dto.subscription.SubscriptionGetDto;
import com.kasperovich.laelectronics.api.mapping.converters.subscription.SubscriptionUpdateConverter;
import com.kasperovich.laelectronics.api.mapping.mappers.SubscriptionListMapper;
import com.kasperovich.laelectronics.api.mapping.mappers.SubscriptionMapper;
import com.kasperovich.laelectronics.exception.NotDeletableStatusException;
import com.kasperovich.laelectronics.models.Subscription;
import com.kasperovich.laelectronics.repository.SubscriptionRepository;
import com.kasperovich.laelectronics.service.product.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Validated
@Slf4j
@RequestMapping("/data/products")
@RequiredArgsConstructor
@Tag(name = "Subscriptions")
@CrossOrigin
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubscriptionController {

    SubscriptionService subscriptionService;

    SubscriptionMapper subscriptionMapper;

    SubscriptionListMapper subscriptionListMapper;

    SubscriptionUpdateConverter subscriptionUpdateConverter;

    SubscriptionRepository subscriptionRepository;

    @Operation(
            summary = "Gets all subscriptions",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found subscriptions",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = SubscriptionGetDto.class))))
            })
    @GetMapping
    public ResponseEntity<List<SubscriptionGetDto>>findAll(){
        List<Subscription>entites= subscriptionService.findAll();
        List<SubscriptionGetDto>dtos=new ArrayList<>(subscriptionListMapper.toDto(entites));
        return ResponseEntity.ok(dtos);
    }

    @Operation(
            summary = "Create subscription(Admin only)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Created",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = SubscriptionGetDto.class)))
                            })
            }
            ,parameters = {
            @Parameter(
                    in = ParameterIn.HEADER,
                    name = "X-Auth-Token",
                    required = true,
                    description = "JWT Token, can be generated in auth controller /auth")
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Transactional
    @PostMapping
    public ResponseEntity<Map<String, SubscriptionGetDto> >createProduct(@RequestBody SubscriptionCreateDto subscriptionCreateDto){
        Subscription subscription = subscriptionMapper.toEntity(subscriptionCreateDto);
        Subscription createdSubscription = subscriptionService.createProduct(subscription);
        SubscriptionGetDto subscriptionGetDto = subscriptionMapper.toDto(createdSubscription);
        return new ResponseEntity<>(
                Collections.singletonMap("New subscription:", subscriptionGetDto),
                HttpStatus.CREATED
        );
    }


    @Operation(
            summary = "Update subscription(Admin only)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Subscription updated",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = SubscriptionGetDto.class))))
            },parameters = {
            @Parameter(
                    in = ParameterIn.HEADER,
                    name = "X-Auth-Token",
                    required = true,
                    description = "JWT Token, can be generated in auth controller /auth")
    })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Transactional
    @PatchMapping("/update")
    public ResponseEntity<Map<String, SubscriptionGetDto>>updateProduct(@RequestParam String id, @RequestBody SubscriptionCreateDto subscriptionCreateDto){
        Long iD=Long.parseLong(id);
        Subscription subscription = subscriptionUpdateConverter.doConvert(subscriptionCreateDto, iD);
        Subscription updatedSubscription = subscriptionService.updateProduct(subscription);
        return ResponseEntity.ok(Collections.singletonMap("Updated subscription:", subscriptionMapper.toDto(updatedSubscription)));
    }


    @Operation(
            summary = "Delete subscription(Admin only)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Subscription deleted",
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
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/delete")
    public ResponseEntity<DeleteSubscriptionDto>deleteProduct(@RequestParam(value = "ID") String idStr) throws NotDeletableStatusException {
        Long id=Long.parseLong(idStr);
        subscriptionService.deleteProduct(id);
        return ResponseEntity.ok(new DeleteSubscriptionDto(DeleteSubscriptionDto.DeletedStatus.DELETED, id));
    }

    @Operation(
            summary = "Find subscription by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Subscription returned",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = SubscriptionGetDto.class))))
            })
    @PatchMapping("/product")
    public ResponseEntity<SubscriptionGetDto>findProductById(@RequestParam(value = "ID") String id) {
        Subscription subscription = subscriptionRepository.findProductByIdAndIsDeleted(Long.parseLong(id), false)
                .orElseThrow(()->new EntityNotFoundException("Subscription with this ID does not exist"));
        SubscriptionGetDto subscriptionGetDto = SubscriptionGetDto
                .builder()
                .id(subscription.getId())
                .name(subscription.getName())
                .price(subscription.getPrice())
                .build();

        return ResponseEntity.ok(subscriptionGetDto);
    }

}
