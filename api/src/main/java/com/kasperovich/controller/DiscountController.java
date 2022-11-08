package com.kasperovich.controller;

import com.kasperovich.dto.discount.DiscountCreateDto;
import com.kasperovich.dto.discount.DiscountGetDto;
import com.kasperovich.dto.users.UserGetDto;
import com.kasperovich.mapping.converters.discount.DiscountCreateConverter;
import com.kasperovich.mapping.converters.discount.DiscountGetConverter;
import com.kasperovich.models.Discount;
import com.kasperovich.service.discount.DiscountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/data/discounts")
@RequiredArgsConstructor
@Tag(name = "discounts")
public class DiscountController {

    private final DiscountService discountService;

    private final DiscountCreateConverter discountCreateConverter;

    private final DiscountGetConverter discountGetConverter;

    @PostMapping
    public ResponseEntity<Map<String, DiscountGetDto>>createDiscount(@RequestBody DiscountCreateDto discountCreateDto){
        Discount discount=discountCreateConverter.convert(discountCreateDto);
        discountService.createDiscount(discount);
        return new ResponseEntity<>(Collections.singletonMap("Created discount:", discountGetConverter.convert(discount)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DiscountGetDto>>findAllDiscount(){
        List<DiscountGetDto>list=discountService.findAll()
                .stream().map(discountGetConverter::convert).collect(Collectors.toList());
        return  ResponseEntity.ok(list);
    }

    @PatchMapping
    public ResponseEntity<String>deleteDiscount(@RequestParam String id){
        discountService.deleteDiscount(Long.parseLong(id));
        return ResponseEntity.ok("Disocunt with id"+ id+" deleted");
    }




}
