package com.gateway.security;

import com.gateway.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        // @formatter:off
        http
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .csrf()
                    .disable()
                .cors()
                    .and()
                .authorizeExchange()
                    .pathMatchers("/login", "/actuator/**", "/signup/user", "/token/validate")
                        .permitAll()
                    .pathMatchers(HttpMethod.POST, "/signup/courier")
                        .hasAuthority(Role.ADMIN.name())
                    .pathMatchers(HttpMethod.GET, ("/couriers"))
                        .hasAuthority(Role.ADMIN.name())
                    .pathMatchers(HttpMethod.POST, ("/parcel/assign-courier"))
                        .hasAuthority(Role.ADMIN.name())
                    .pathMatchers(HttpMethod.PUT, ("/parcel/change-destination"))
                        .hasAuthority(Role.ADMIN.name())
                    .pathMatchers(HttpMethod.POST, ("/parcel/change-parcel-status-for-couriers"))
                        .hasAuthority(Role.COURIER.name())
                    .pathMatchers(HttpMethod.GET, ("/parcel/by-id"))
                        .hasAnyAuthority(Role.COURIER.name(), Role.USER.name(), Role.ADMIN.name())
                    .pathMatchers(HttpMethod.GET, ("/parcel/by-order-id"))
                        .hasAnyAuthority(Role.COURIER.name(), Role.USER.name(), Role.ADMIN.name())
                    .pathMatchers(HttpMethod.GET, ("/parcel/coordinates"))
                        .hasAnyAuthority(Role.COURIER.name(), Role.USER.name(), Role.ADMIN.name())
                    .pathMatchers(HttpMethod.PUT, ("/parcel/coordinates"))
                        .hasAuthority(Role.COURIER.name())
                    .pathMatchers(HttpMethod.GET, ("/parcel/all-by-courier-id"))
                        .hasAuthority(Role.ADMIN.name())
                    .pathMatchers(HttpMethod.GET, ("/parcel/by-courier-id"))
                        .hasAnyAuthority(Role.COURIER.name(), Role.ADMIN.name())
                    .pathMatchers(HttpMethod.POST, ("/order/create"))
                        .hasAuthority(Role.USER.name())
                    .pathMatchers(HttpMethod.POST, ("/order/cancel"))
                        .hasAuthority(Role.ADMIN.name())
                    .pathMatchers(HttpMethod.GET, ("/order/by-user"))
                        .hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                    .pathMatchers(HttpMethod.PUT, ("/order/status"))
                        .hasAuthority(Role.ADMIN.name())
                    .pathMatchers(HttpMethod.GET, ("/order/all"))
                        .hasAuthority(Role.ADMIN.name())
                .anyExchange()
                    .authenticated();
        return http.build();
    }
}
