package com.auth.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.token.validity}")
    private Long tokenValidity;

    public Optional<String> generateToken(String username, Long userId) {
        Instant tokenIssuedAt = new Date().toInstant();
        Instant tokenExpiresAt = tokenIssuedAt.plus(tokenValidity, ChronoUnit.MILLIS);

        return Optional.ofNullable(JWT.create()
                .withClaim("username", username)
                .withClaim("userId", userId)
                .withIssuedAt(tokenIssuedAt)
                .withExpiresAt(tokenExpiresAt)
                .sign(Algorithm.HMAC256(secret)));
    }

    public Optional<String> validateTokenAndRetrieveUsername(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return Optional.ofNullable(jwt.getClaim("username").asString());
    }
}
