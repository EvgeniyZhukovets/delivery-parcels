package com.auth.controller;

import com.auth.dto.request.LoginRequest;
import com.auth.dto.request.SignUpRequest;
import com.auth.dto.response.LoginResponse;
import com.auth.dto.response.ValidateTokenResponse;
import com.auth.enums.Role;
import com.auth.service.AuthService;
import com.common.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/signup/user")
    public StandardResponse signUpUser(@RequestBody SignUpRequest request) {
        service.signUp(request, Role.USER);
        return new StandardResponse();
    }

    @PostMapping("/signup/courier")
    public StandardResponse signUpCourier(@RequestBody SignUpRequest request) {
        service.signUpCourier(request, Role.COURIER);
        return new StandardResponse();
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return service.login(request);
    }

    @PostMapping("/token/validate")
    public ValidateTokenResponse validateToken(@RequestHeader("Authorization") String token) {
        return service.validateToken(token);
    }
}
