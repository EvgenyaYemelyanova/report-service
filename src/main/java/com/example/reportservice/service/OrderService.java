package com.example.reportservice.service;

import com.example.reportservice.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    Order createOrder(Order order);
    Order getOrderById(long id);
    Order updateOrder(long id, Order order);
    void deleteOrder(long id);
    List<Order> getAllOrders();
    List<Order> getAllOrdersByDate(LocalDateTime from, LocalDateTime to);
}
