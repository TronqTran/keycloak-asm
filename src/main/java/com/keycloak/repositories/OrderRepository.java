package com.keycloak.repositories;

import com.keycloak.models.Order;
import com.keycloak.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByRestaurant(Restaurant restaurant);
}
