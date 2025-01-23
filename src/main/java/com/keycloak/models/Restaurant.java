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
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "restaurant_id")
    public Long id;
    @Column(name = "restaurant_name", nullable = false)
    private String name;
    @Column(name = "restaurant_location", nullable = false)
    private String location;
    @Column(name = "restaurant_type")
    private String type;
}
