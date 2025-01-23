package com.keycloak.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.keycloak.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;
    @Column(name = "order_time")
    private Instant orderTime;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

}
