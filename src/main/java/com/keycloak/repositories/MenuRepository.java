package com.keycloak.repositories;

import com.keycloak.models.Menu;
import com.keycloak.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    Iterable<Menu> findByRestaurant(Restaurant restaurant);
}
