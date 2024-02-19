//package com.kasperovich.laelectronics.api.mapping.converters.order;
//
//import com.kasperovich.laelectronics.api.dto.order.OrderUpdateDto;
//import com.kasperovich.laelectronics.api.mapping.converters.payment.PaymentUpdateConverter;
//import com.kasperovich.laelectronics.models.Order;
//import com.kasperovich.laelectronics.models.Subscription;
//import com.kasperovich.laelectronics.repository.OrderRepository;
//import com.kasperovich.laelectronics.repository.SubscriptionRepository;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import java.util.stream.Collectors;
//
//@Component
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class OrderUpdateConverter implements Converter<OrderUpdateDto, Order> {
//
//    OrderRepository orderRepository;
//    SubscriptionRepository subscriptionRepository;
//    PaymentUpdateConverter paymentUpdateConverter;
//
//
//    @Override
//    public Order convert(OrderUpdateDto orderUpdateDto){
//        return  null;
//    }
//
//    public Order doConvert(OrderUpdateDto orderUpdateDto, Long id) throws Exception {
//
//        Order order=orderRepository.findById(id).orElseThrow(()->new EntityNotFoundException("No order with this ID"));
//
//
//        if(orderUpdateDto.getProducts()!=null){
//            order.setSubscriptions(orderUpdateDto
//                    .getProducts()
//                    .stream()
//                    .map(x-> subscriptionRepository.findById(x)
//                            .orElseThrow(()->new EntityNotFoundException("No product with this ID")))
//                    .collect(Collectors.toSet()));
//        }
//
//        order.setTotal(order.getSubscriptions().stream().map(Subscription::getPrice).reduce(Long::sum).orElseThrow(Exception::new));
//
//        if(orderUpdateDto.getPayment()!=null){
//            order.setPayment(paymentUpdateConverter.convert(orderUpdateDto.getPayment(), order.getPayment().getId()));
//        }
//        return order;
//
//    }
//}
