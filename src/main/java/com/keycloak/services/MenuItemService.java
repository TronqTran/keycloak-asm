package com.keycloak.services;

import com.keycloak.models.Menu;
import com.keycloak.models.MenuItem;
import com.keycloak.repositories.MenuItemRepository;
import com.keycloak.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private MenuRepository menuRepository;

    public Iterable<MenuItem> getMenuItemsByMenu(Menu menu) {
        return menuItemRepository.findByMenu(menu);
    }

    public MenuItem updateMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }
}
