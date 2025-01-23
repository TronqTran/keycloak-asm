package com.keycloak.controllers;

import com.keycloak.models.Menu;
import com.keycloak.models.MenuItem;
import com.keycloak.models.Restaurant;
import com.keycloak.services.MenuItemService;
import com.keycloak.services.MenuService;
import com.keycloak.services.RestaurantService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
@SecurityRequirement(name = "Keycloak")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuItemService menuItemService;

    // Public endpoints
    @GetMapping("/public/list")
    public ResponseEntity<Iterable<Restaurant>> getRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }
    // Public endpoints
    @GetMapping("/public/menu/{restaurantId}")
    public ResponseEntity<Iterable<Menu>> getRestaurantMenu(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId).orElse(null);
        if (restaurant != null) {
            Iterable<Menu> menu = menuService.getRestaurantMenu(restaurant);
            return ResponseEntity.ok(menu);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/public/menu/menu-item/{restaurantId}")
    public ResponseEntity<Iterable<MenuItem>> getRestaurantMenuItem(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId).orElse(null);
        Iterable<Menu> menu = menuService.getRestaurantMenu(restaurant);

        if (restaurant != null) {
           menu.iterator().next();
            Iterable<MenuItem> menuItem = menuItemService.getMenuItemsByMenu(menu.iterator().next());
            if (menuItem != null) {
                return ResponseEntity.ok(menuItem);
            }
        }

        return ResponseEntity.badRequest().build();
    }


    // Admin endpoints
    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        restaurantService.createRestaurant(restaurant);
        return ResponseEntity.ok(restaurant);
    }

    // Manager endpoints
    @PutMapping
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant) {
        restaurantService.createRestaurant(restaurant);
        return ResponseEntity.ok(restaurant);
    }

}
