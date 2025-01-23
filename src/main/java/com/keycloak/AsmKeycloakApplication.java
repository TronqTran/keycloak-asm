package com.keycloak;

import com.keycloak.models.*;
import com.keycloak.models.pk.OrderItemPk;
import com.keycloak.repositories.*;
import com.keycloak.services.OrderService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@SecurityScheme(
        name = "Keycloak",
        openIdConnectUrl = "http://localhost:8080/realms/asm-keycloak/.well-known/openid-configuration",
        scheme = "bearer",
        type = SecuritySchemeType.OPENIDCONNECT,
        in = SecuritySchemeIn.HEADER
)
public class AsmKeycloakApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsmKeycloakApplication.class, args);
    }

//    @Autowired
//    private RestaurantRepository restaurantRepository;
//    @Autowired
//    private MenuRepository menuRepository;
//    @Autowired
//    private MenuItemRepository menuItemRepository;
//    @Autowired
//    private OrderRepository orderRepository;
//    @Autowired
//    private OrderItemRepository orderItemRepository;
//    @Bean
//    CommandLineRunner runner () {
//        return args -> {
//            Faker faker = new Faker();
//
//                for (int i = 0; i < 5; i++) {
//
//                    Restaurant restaurant = new Restaurant();
//                    restaurant.setName(faker.restaurant().name());
//                    restaurant.setLocation(faker.address().fullAddress());
//                    restaurant.setType(faker.restaurant().type());
//                    restaurantRepository.save(restaurant);
//
//                    for (int j = 0; j < 2; j++) {
//                        Menu menu = new Menu();
//                        menu.setName(faker.food().ingredient());
//                        menu.setRestaurant(restaurant);
//                        menu.setActive(true);
//                        menuRepository.save(menu);
//
//                        for (int k = 0; k < 5; k++) {
//                            MenuItem menuItem = new MenuItem();
//                            menuItem.setName(faker.food().dish());
//                            menuItem.setPrice(faker.number().randomDouble(2, 1, 100));
//                            menuItem.setDescription(faker.coffee().descriptor());
//                            menuItem.setMenu(menu);
//                            menuItemRepository.save(menuItem);
//
//                            Order order = new Order();
//                            order.setOrderTime(faker.timeAndDate().future());
//                            orderRepository.save(order);
//
//                            OrderItemPk orderItemPk = new OrderItemPk();
//                            orderItemPk.setMenuItemId(menuItem.getId());
//                            orderItemPk.setOrderId(order.getId());
//
//                            OrderItem orderItem = new OrderItem();
//                            orderItem.setId(orderItemPk);
//                            orderItem.setOrder(order);
//                            orderItem.setMenuItem(menuItem);
//                            orderItem.setQuantity(faker.number().numberBetween(1, 5));
//                            orderItemRepository.save(orderItem);
//                        }
//                    }
//            }
//
//        };
//    }
}
