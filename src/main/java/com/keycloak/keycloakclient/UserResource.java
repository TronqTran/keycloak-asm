package com.keycloak.keycloakclient;

import com.keycloak.dto.User;
import com.keycloak.security.KeycloakSecurityUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.common.util.CollectionUtil;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/keycloak")
@SecurityRequirement(name = "Keycloak")
public class UserResource {
    @Autowired
    KeycloakSecurityUtil keycloakSecurityUtil;

    @Value("${realm}")
    private String realm;

    @GetMapping
    @RequestMapping("/users")
    public List<User> getUsers() {
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        List<UserRepresentation> userRepresentations = keycloak.realm(realm).users().list();
        return mapUsers(userRepresentations);
    }

    @GetMapping(value = "/users/{id}")
    public User getUser(@PathVariable("id") String id) {
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        return mapUser(keycloak.realm(realm).users().get(id).toRepresentation());
    }

    @PostMapping(value = "/user")
    public Response createUser(User user) {
        UserRepresentation userRep = mapUserRep(user);
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        Response res = keycloak.realm(realm).users().create(userRep);
        return Response.ok(user).build();
    }

    @PutMapping(value = "/user")
    public Response updateUser(User user) {
        UserRepresentation userRep = mapUserRep(user);
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        keycloak.realm(realm).users().get(user.getId()).update(userRep);
        return Response.ok(user).build();
    }

    @DeleteMapping(value = "/users/{id}")
    public Response deleteUser(@PathVariable("id") String id) {
        Keycloak keycloak = keycloakSecurityUtil.getKeycloakInstance();
        keycloak.realm(realm).users().delete(id);
        return Response.ok().build();
    }


    private UserRepresentation mapUserRep(User user) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setId(user.getId());
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setFirstName(user.getFirstName());
        userRepresentation.setLastName(user.getLastName());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);
        List<CredentialRepresentation>  credentialRepresentations = new ArrayList<>();
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setValue(user.getPassword());
        credentialRepresentations.add(credentialRepresentation);
        userRepresentation.setCredentials(credentialRepresentations);
        return userRepresentation;
    }


    private List<User> mapUsers(List<UserRepresentation> userRepresentations) {
        List<User> users = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(userRepresentations)){
            userRepresentations.forEach(
                    userRep -> users.add(mapUser(userRep))
            );
        }
        return users;
    }


    private User mapUser (UserRepresentation userRepresentation) {
        User user = new User();
        user.setId(userRepresentation.getId());
        user.setFirstName(userRepresentation.getFirstName());
        user.setLastName(userRepresentation.getLastName());
        user.setUsername(userRepresentation.getUsername());
        user.setEmail(userRepresentation.getEmail());
        return user;
    }
}
