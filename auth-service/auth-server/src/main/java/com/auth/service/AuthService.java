package com.auth.service;

import com.auth.dto.request.LoginRequest;
import com.auth.dto.request.SignUpRequest;
import com.auth.dto.response.LoginResponse;
import com.auth.dto.response.ValidateTokenResponse;
import com.auth.enums.Role;

public interface AuthService {

    void signUp(SignUpRequest request, Role role);

    void signUpCourier(SignUpRequest request, Role role);


    LoginResponse login(LoginRequest request);

    ValidateTokenResponse validateToken(String token);
}
