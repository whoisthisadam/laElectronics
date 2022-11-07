package com.kasperovich.service.order;

import com.kasperovich.exception.NotDeletableStatusException;
import com.kasperovich.models.Order;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;


public interface OrderService {

    public List<Order> findAll();

    public Order createOrder(Order order);

    public Order updateOrder(Order order);

    public Order deleteOrder(Long id) throws NotDeletableStatusException;

}
