package com.gateway.controller;

import com.common.StandardResponse;
import com.gateway.clients.AuthFeignClient;
import com.gateway.dto.auth.request.LoginRequest;
import com.gateway.dto.auth.request.SignUpRequest;
import com.gateway.dto.auth.response.LoginResponse;
import com.gateway.dto.auth.response.ValidateTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Log4j2
public class AuthController {

    private final AuthFeignClient feignClient;

    @PostMapping("/signup/user")
    public StandardResponse signUpUser(@RequestBody SignUpRequest request) {
        log.info("signUpUser called");
        return feignClient.signUpUser(request);
    }

    @PostMapping("/signup/courier")
    public StandardResponse signUpCourier(@RequestBody SignUpRequest request) {
        log.info("signUpCourier called");
        return feignClient.signUpCourier(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        log.info("login called");
        return feignClient.login(request);
    }

    @PostMapping("/token/validate")
    public ValidateTokenResponse validateToken(@RequestHeader("Authorization") String token) {
        log.info("validateToken called");
        return feignClient.validateToken(token);
    }
}
