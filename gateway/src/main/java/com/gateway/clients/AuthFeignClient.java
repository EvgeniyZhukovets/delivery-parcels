package com.gateway.clients;


import com.common.StandardResponse;
import com.gateway.dto.auth.request.LoginRequest;
import com.gateway.dto.auth.request.SignUpRequest;
import com.gateway.dto.auth.response.LoginResponse;
import com.gateway.dto.auth.response.ValidateTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authFeignClient", url = "${delivery.auth.address}")
public interface AuthFeignClient {

    @PostMapping("/auth/signup/user")
    StandardResponse signUpUser(@RequestBody SignUpRequest request);

    @PostMapping("/auth/signup/courier")
    StandardResponse signUpCourier(@RequestBody SignUpRequest request);

    @PostMapping("/auth/login")
    LoginResponse login(@RequestBody LoginRequest request);

    @PostMapping("/auth/token/validate")
    ValidateTokenResponse validateToken(@RequestHeader("Authorization") String token);
}
