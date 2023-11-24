package com.example.Lab09.service;

import com.example.Lab09.model.Order;

public interface OrderService {
    Iterable<Order> getAllOrders();
    Order create(Order order);
    void update(Order order);
    Order getOrder(Long id);
    void removeById(Long id);
}
