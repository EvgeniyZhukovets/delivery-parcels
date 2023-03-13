package com.gateway.security;

import com.gateway.clients.AuthFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final AuthFeignClient feignClient;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication.getCredentials().toString())
                .map(feignClient::validateToken)
                .map(response -> new UsernamePasswordAuthenticationToken(
                        response.username(),
                        null,
                        Stream.of(response.role()).map(SimpleGrantedAuthority::new).toList()
                ));
    }
}
