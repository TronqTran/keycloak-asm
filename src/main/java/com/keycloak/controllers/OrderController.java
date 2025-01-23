package com.keycloak.controllers;

import com.keycloak.enums.OrderStatus;
import com.keycloak.models.Order;
import com.keycloak.models.OrderItem;
import com.keycloak.models.Restaurant;
import com.keycloak.services.OrderService;
import com.keycloak.services.RestaurantService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
@SecurityRequirement(name = "Keycloak")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/{orderId}")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrderDetails(@PathVariable Long orderId) {
        Optional<Order> order = orderService.getOrderDetails(orderId);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/restaurant/{restaurantId}")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Order>> getOrdersByRestaurant(@PathVariable Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(restaurantId);
        if (!restaurant.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Order> orders = orderService.getOrderByRestaurant(restaurant.get());
        return ResponseEntity.ok(orders);
    }


    @PostMapping("/restaurant/{restaurantId}")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createOrder(@RequestBody Order order, @PathVariable Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(restaurantId);
        if (!restaurant.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        order.setRestaurant(restaurant.get());
        Order newOrder = orderService.createOrder(order);
        return ResponseEntity.ok(newOrder);
    }


    @PutMapping
    @RequestMapping("/{orderId}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        Optional<Order> order = orderService.getOrderDetails(orderId);
        if (!order.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        order.get().setStatus(OrderStatus.valueOf(status));
        Order updatedOrder = orderService.updateOrder(order.get());
        return ResponseEntity.ok(updatedOrder);
    }


    @GetMapping("/{orderId}/items")
    @Procedure(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderItem>> getOrderItemsByOrderId(@PathVariable Long orderId) {
        Iterable<OrderItem> order = orderService.getOrderDetailsByOrderId(orderId);
        if (!order.iterator().hasNext()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok((List<OrderItem>) order);
    }
}
