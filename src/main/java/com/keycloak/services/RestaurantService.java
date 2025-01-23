package com.keycloak.services;

import com.keycloak.models.Restaurant;
import com.keycloak.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public Optional<Restaurant> getRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }

    public Iterable<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
}
