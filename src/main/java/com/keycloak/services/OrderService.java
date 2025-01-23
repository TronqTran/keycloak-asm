package com.keycloak.services;

import com.keycloak.models.Order;
import com.keycloak.models.OrderItem;
import com.keycloak.models.Restaurant;
import com.keycloak.repositories.OrderItemRepository;
import com.keycloak.repositories.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderDetails(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderByRestaurant(Restaurant restaurant) {
        return orderRepository.findByRestaurant(restaurant);
    }

    public Iterable<OrderItem> getOrderDetailsByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        return orderItemRepository.findByOrder(order);
    }
}
