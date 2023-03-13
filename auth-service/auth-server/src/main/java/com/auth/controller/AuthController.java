package com.auth.controller;

import com.auth.dto.request.LoginRequest;
import com.auth.dto.request.SignUpRequest;
import com.auth.dto.response.LoginResponse;
import com.auth.dto.response.ValidateTokenResponse;
import com.auth.service.AuthService;
import com.common.StandardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Log4j2
public class AuthController {

    private final AuthService service;

    @PostMapping("/signup/user")
    public StandardResponse signUpUser(@RequestBody SignUpRequest request) {
        log.info("signUpUser called with username: {}", request.getUsername());
        service.signUp(request);
        return new StandardResponse();
    }

    @PostMapping("/signup/courier")
    public StandardResponse signUpCourier(@RequestBody SignUpRequest request) {
        log.info("signUpCourier called with username: {}", request.getUsername());
        service.signUpCourier(request);
        return new StandardResponse();
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        log.info("login called with username: {}", request.getUsername());
        return service.login(request);
    }

    @PostMapping("/token/validate")
    public ValidateTokenResponse validateToken(@RequestHeader("Authorization") String token) {
        return service.validateToken(token);
    }
}
