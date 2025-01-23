package com.keycloak.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "menu_item_id")
    private Long id;
    @Column(name = "menu_item_name", nullable = false)
    private String name;
    private String description;
    private Double price;
    private Boolean active;
    @Column(name = "image_url")
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    @JsonIgnore
    private Menu menu;
}
