package com.gateway.controller;

import com.common.StandardResponse;
import com.gateway.clients.AuthFeignClient;
import com.gateway.dto.auth.request.LoginRequest;
import com.gateway.dto.auth.request.SignUpRequest;
import com.gateway.dto.auth.response.LoginResponse;
import com.gateway.dto.auth.response.ValidateTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthFeignClient feignClient;

    @PostMapping("/signup/user")
    public StandardResponse signUpUser(@RequestBody SignUpRequest request) {
        return feignClient.signUpUser(request);
    }

    @PostMapping("/signup/courier")
    public StandardResponse signUpCourier(@RequestBody SignUpRequest request) {
        return feignClient.signUpCourier(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return feignClient.login(request);
    }

    @PostMapping("/token/validate")
    public ValidateTokenResponse validateToken(@RequestHeader("Authorization") String token) {
        return feignClient.validateToken(token);
    }
}
