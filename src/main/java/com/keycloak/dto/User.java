package com.keycloak.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
