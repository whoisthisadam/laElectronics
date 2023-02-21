package com.kasperovich.desoccer.service.order;

import com.kasperovich.desoccer.exception.NotDeletableStatusException;
import com.kasperovich.desoccer.models.Order;

import java.util.List;


public interface OrderService {

    List<Order> findAll();

    Order createOrder(Order order);

    Order updateOrder(Order order);

    Order deleteOrder(Long id) throws NotDeletableStatusException;

}
