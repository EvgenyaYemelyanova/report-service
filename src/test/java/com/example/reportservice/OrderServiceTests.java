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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

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
        Order order = new Order(
                1L, 2L, 3L, 3L,
                LocalDateTime.now(), LocalDateTime.now());

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
}
