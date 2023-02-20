package com.kasperovich.service.order;

import com.kasperovich.exception.NotDeletableStatusException;
import com.kasperovich.models.Order;

import java.util.List;


public interface OrderService {

    List<Order> findAll();

    Order createOrder(Order order);

    Order updateOrder(Order order);

    Order deleteOrder(Long id) throws NotDeletableStatusException;

}
