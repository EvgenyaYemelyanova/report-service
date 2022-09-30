package com.example.reportservice;

import com.example.reportservice.model.Order;
import com.example.reportservice.repositories.OrderRepository;
import com.example.reportservice.service.OrderService;
import com.example.reportservice.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {

    @Mock
    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    void initUseOrder() {
        orderService = new OrderServiceImpl(orderRepository);
    }

    @Test
    public void savedOrder() {
        long orderId = 1;
        LocalDateTime time = LocalDateTime.now();
        Order order = new Order(orderId, 2L, 3L, 3L, time, time);

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order savedOrder = orderService.createOrder(order);
        assertThat(savedOrder.getFrom()).isNotNull();
        System.out.println(order);
    }

    @Test
    public void checkOrderOnNull() {
        Order order = new Order(
                null, null, null, null, null, null);

        lenient().when(orderRepository.save(order)).thenThrow(NullPointerException.class);
    }

    @Test
    public void getOrderById() {
        long orderId = 1;
        LocalDateTime time = LocalDateTime.now();
        Order order = new Order(orderId, 2L, 3L, 3L, time, time);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order savedOrder = orderService.getOrderById(1L);
        assertThat(savedOrder.getId()).isNotNull();
        System.out.println(order);
    }

    @Test
    public void deleteOrder() {
        long orderId = 1;
        LocalDateTime time = LocalDateTime.now();
        Order order = new Order(orderId, 2L, 3L, 3L, time, time);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        orderService.deleteOrder(orderId);
        verify(orderRepository).delete(order);
    }

    @Test
    public void getAllOrders() {
        LocalDateTime time = LocalDateTime.now();
        when(orderRepository.findAll()).thenReturn(Arrays.asList(
                new Order(1L, 2L, 3L, 4L, time, time),
                new Order(5L, 6L, 7L, 8L, time, time)
        ));

        List<Order> getAllOrders = orderService.getAllOrders();
        assertEquals(2, getAllOrders.size());
        verify(orderRepository).findAll();
    }

    @Test
    public void getAllOrdersByDate() {
        LocalDateTime myTime = LocalDateTime.of(2023, Month.FEBRUARY,3,6,30,40,50000);
        LocalDateTime fromTime = LocalDateTime.of(2022, Month.FEBRUARY,3,6,30,40,50000);
        LocalDateTime toTime = LocalDateTime.of(2022, Month.FEBRUARY,3,6,30,40,50000);
        when(orderRepository.getAllOrdersWhereDatesBetween(fromTime, toTime)).thenReturn(Arrays.asList(
                new Order(1L, 2L, 3L, 4L, fromTime, toTime),
                new Order(5L, 6L, 7L, 8L, myTime, myTime)
        ));

        List<Order> getAllOrders = orderService.getAllOrdersByDate(fromTime, toTime);
        verify(orderRepository).getAllOrdersWhereDatesBetween(fromTime, toTime);
        System.out.println(getAllOrders);
    }
}
