package com.auth.service;

import com.auth.dto.request.LoginRequest;
import com.auth.dto.request.SignUpRequest;
import com.auth.dto.response.LoginResponse;
import com.auth.dto.response.ValidateTokenResponse;

public interface AuthService {

    void signUp(SignUpRequest request);

    void signUpCourier(SignUpRequest request);

    LoginResponse login(LoginRequest request);

    ValidateTokenResponse validateToken(String token);
}
