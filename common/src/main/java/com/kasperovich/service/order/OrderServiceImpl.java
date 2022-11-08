package com.kasperovich.service.order;

import com.kasperovich.enums.OrderStatus;
import com.kasperovich.enums.PaymentStatus;
import com.kasperovich.exception.NotDeletableStatusException;
import com.kasperovich.models.Edit;
import com.kasperovich.models.Order;
import com.kasperovich.repository.OrderRepository;
import com.kasperovich.repository.PaymentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderServiceImpl implements OrderService{

    OrderRepository orderRepository;

    PaymentRepository paymentRepository;
    @Override
    public List<Order> findAll() {
        return orderRepository.findAll().stream().filter(x-> !x.getIsDeleted()).collect(Collectors.toList());
    }

    @Override
    public Order createOrder(Order order) {
        if(order.getPayment().getStatus()==PaymentStatus.NOT_PAID){
            order.setOrderStatus(OrderStatus.NOT_STARTED);
        }
        else order.setOrderStatus(OrderStatus.IN_PROGRESS);
        order=orderRepository.save(order);
        paymentRepository.save(order.getPayment());
        return order;
    }

    @Override
    public Order updateOrder(Order order) {
        order.setEditData(new Edit(order.getEditData().getCreationDate(), new Timestamp(new Date().getTime())));
        order=orderRepository.save(order);
        paymentRepository.save(order.getPayment());
        return order;
    }

    @Override
    public Order deleteOrder(Long id) throws NotDeletableStatusException {
        Order order=orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(order.getOrderStatus()==OrderStatus.DONE||order.getOrderStatus()==OrderStatus.CANCELLED){
            order.setIsDeleted(true);
            return orderRepository.save(order);
        }
        else throw new NotDeletableStatusException(
                "Unable to delete order with status "+order.getOrderStatus().toString()
        );
    }
}
