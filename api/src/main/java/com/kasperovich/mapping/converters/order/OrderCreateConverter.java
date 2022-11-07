package com.kasperovich.mapping.converters.order;

import com.kasperovich.dto.order.OrderCreateDto;
import com.kasperovich.dto.product.ProductGetDto;
import com.kasperovich.enums.PaymentStatus;
import com.kasperovich.mapping.converters.payment.PaymentCreateConverter;
import com.kasperovich.models.Edit;
import com.kasperovich.models.Order;
import com.kasperovich.repository.PaymentRepository;
import com.kasperovich.repository.ProductRepository;
import com.kasperovich.repository.UserRepository;
import com.kasperovich.service.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.weaver.ast.Or;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderCreateConverter {

    UserService userService;

    UserRepository userRepository;

    PaymentCreateConverter paymentCreateConverter;

    ProductRepository productRepository;

    public Order convert(OrderCreateDto orderCreateDto) {
        Order order=new Order();
        order.setUser(userRepository.findById(orderCreateDto.getUserId())
                .orElseThrow(EntityNotFoundException::new));
        order.setTotal(
                orderCreateDto
                        .getProducts()
                        .stream()
                        .map(x->productRepository.findById(x)
                                .orElseThrow(EntityNotFoundException::new).getPrice())
                        .reduce(0L,Long::sum)
        );
        order.setProducts(orderCreateDto.getProducts().stream().map(x->productRepository.findById(x).orElseThrow(EntityNotFoundException::new)).collect(Collectors.toSet()));
        order.setEditData(new Edit(new Timestamp(new Date().getTime()), null));

        order.setPayment(paymentCreateConverter.convert(orderCreateDto.getPayment(), order));
        return order;
    }
}
