package com.keycloak.models;

import com.keycloak.models.pk.OrderItemPk;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class OrderItem {
    @EmbeddedId
    private OrderItemPk id;

    @MapsId("menuItemId")
    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    @MapsId("orderId")
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer quantity;

}
