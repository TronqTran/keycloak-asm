package com.keycloak.models.pk;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class OrderItemPk implements Serializable {
    private Long orderId;
    private Long menuItemId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPk that = (OrderItemPk) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(menuItemId, that.menuItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, menuItemId);
    }
}
