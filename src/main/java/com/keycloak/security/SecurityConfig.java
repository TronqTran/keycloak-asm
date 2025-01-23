package com.keycloak.security;

import org.keycloak.adapters.authorization.integration.jakarta.ServletPolicyEnforcerFilter;
import org.keycloak.adapters.authorization.spi.ConfigurationResolver;
import org.keycloak.adapters.authorization.spi.HttpRequest;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.keycloak.util.JsonSerialization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http.csrf(t -> t.disable());
        http.oauth2ResourceServer(t -> t.jwt(Customizer.withDefaults()));
        http.addFilterAfter(policyEnforcerFilter(), BearerTokenAuthenticationFilter.class);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    private ServletPolicyEnforcerFilter policyEnforcerFilter () {
        return new ServletPolicyEnforcerFilter(new ConfigurationResolver() {
            @Override
            public PolicyEnforcerConfig resolve(HttpRequest httpRequest) {
                try {
                    return JsonSerialization.readValue(
                            getClass().getResourceAsStream("/policy-enforcer-config.json"),
                            PolicyEnforcerConfig.class
                    );
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
