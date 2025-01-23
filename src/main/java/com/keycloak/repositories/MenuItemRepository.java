package com.keycloak.repositories;

import com.keycloak.models.Menu;
import com.keycloak.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    Iterable<MenuItem> findByMenu(Menu menu);
}
