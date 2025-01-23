package com.keycloak.controllers;

import com.keycloak.models.Menu;

import com.keycloak.models.MenuItem;
import com.keycloak.services.MenuItemService;
import com.keycloak.services.MenuService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/menu")
@SecurityRequirement(name = "Keycloak")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuItemService menuItemService;


    @GetMapping
    @RequestMapping("/menu-item/{menuId}")
    public ResponseEntity<Iterable<MenuItem>> getMenuItemsByMenuId(@PathVariable Long menuId) {
        Menu menu = menuService.getMenuById(menuId).orElse(null);
        if (menu != null) {
            Iterable<MenuItem> menuItem = menuItemService.getMenuItemsByMenu(menu);
            if (menuItem != null) {
                return ResponseEntity.ok(menuItem);
            }
        }
        return ResponseEntity.badRequest().build();
    }


    // Manager endpoints
    @PostMapping
    @RequestMapping("/create")
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) {
        menuService.createMenu(menu);
        return ResponseEntity.ok(menu);
    }

    // Owner endpoints
    @PutMapping
    @RequestMapping("/menu-item/update")
    public ResponseEntity<MenuItem> updateMenuItem(@RequestBody MenuItem menuItem,
                                                   @AuthenticationPrincipal Jwt jwt) {
        String name = jwt.getClaim(AccessToken.PREFERRED_USERNAME);
        String id = jwt.getSubject();
        String iss = jwt.getClaim(JwtClaimNames.ISS);

        menuItemService.updateMenuItem(menuItem);
        return ResponseEntity.ok(menuItem);
    }

}
