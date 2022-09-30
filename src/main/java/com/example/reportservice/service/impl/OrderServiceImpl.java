package com.example.reportservice.service.impl;

import com.example.reportservice.exception.OrderNotFoundException;
import com.example.reportservice.model.Order;
import com.example.reportservice.repositories.OrderRepository;
import com.example.reportservice.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new OrderNotFoundException(String.format("Order with id '%d' not found", id))
        );
    }

    @Override
    public Order updateOrder(long id, Order newOrder) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new OrderNotFoundException(String.format("Order with id '%d' not found", id))
        );

        handleOrderUpdate(order, newOrder);
        orderRepository.save(order);
        return order;
    }

    private void handleOrderUpdate(Order order, Order newOrder) {
        order.setBuildingId(newOrder.getBuildingId());
        order.setApartmentId(newOrder.getApartmentId());
        order.setCustomerId(newOrder.getCustomerId());
        order.setFrom(newOrder.getFrom());
        order.setTo(newOrder.getTo());
    }

    @Override
    public void deleteOrder(long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new OrderNotFoundException(String.format("Order with id '%d' not found", id))
        );
        orderRepository.delete(order);
    }
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getAllOrdersByDate(LocalDateTime from, LocalDateTime to) {
        return orderRepository.getAllOrdersWhereDatesBetween(from, to);
    }
}
