package com.example.reportservice.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    Long buildingId;
    @NotNull
    Long apartmentId;
    @NotNull
    Long customerId;
    @NotNull
    @FutureOrPresent
    @Column(name = "ordered_from")
    LocalDateTime from;
    @NotNull
    @FutureOrPresent
    @Column(name = "ordered_to")
    LocalDateTime to;
}
