package com.keycloak.repositories;

import com.keycloak.models.Order;
import com.keycloak.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Iterable<OrderItem> findByOrder(Order order);
}
