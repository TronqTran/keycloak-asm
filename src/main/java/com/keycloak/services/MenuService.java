package com.keycloak.services;

import com.keycloak.models.Menu;
import com.keycloak.models.MenuItem;
import com.keycloak.models.Restaurant;
import com.keycloak.repositories.MenuItemRepository;
import com.keycloak.repositories.MenuRepository;
import com.keycloak.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public Menu updateMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public void deleteMenu(Menu menu) {
        menuRepository.delete(menu);
    }

    public Iterable<Menu> getRestaurantMenu(Restaurant restaurant) {
        return menuRepository.findByRestaurant(restaurant);
    }

    public Optional<Menu> getMenuById(Long menuId) {
        return menuRepository.findById(menuId);
    }

}
