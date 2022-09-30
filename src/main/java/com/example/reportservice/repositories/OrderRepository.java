package com.example.reportservice.repositories;

import com.example.reportservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getAllOrdersWhereDatesBetween(LocalDateTime from, LocalDateTime to);
}

