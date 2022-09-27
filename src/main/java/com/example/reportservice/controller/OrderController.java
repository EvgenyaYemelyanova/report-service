package com.example.reportservice.controller;

import com.example.reportservice.model.Order;
import com.example.reportservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createCustomer(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrdersByDate(@RequestBody LocalDateTime from, @RequestBody LocalDateTime to) {
        return orderService.getAllOrdersByDate(from, to);
    }
}
