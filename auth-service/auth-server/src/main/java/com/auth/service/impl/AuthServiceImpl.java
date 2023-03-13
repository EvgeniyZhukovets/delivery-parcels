package com.auth.service.impl;

import com.auth.dto.common.UserDto;
import com.auth.dto.request.LoginRequest;
import com.auth.dto.request.SignUpRequest;
import com.auth.dto.response.LoginResponse;
import com.auth.dto.response.ValidateTokenResponse;
import com.auth.entity.User;
import com.auth.enums.CourierStatus;
import com.auth.enums.Role;
import com.auth.exception.InvalidCredentialsException;
import com.auth.exception.ProcessingTokenException;
import com.auth.exception.TokenExpiredException;
import com.auth.exception.UserExistsException;
import com.auth.mapper.UserMapper;
import com.auth.repository.UserRepository;
import com.auth.service.AuthService;
import com.auth.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder encoder;

    @Override
    public void signUp(SignUpRequest request, Role role) {
        User user = validateAndPrepareUserEntityForSignUp(request, role);
        userRepository.save(user);
    }

    @Override
    public void signUpCourier(SignUpRequest request, Role role) {
        User user = validateAndPrepareUserEntityForSignUp(request, role);
        user.setCourierStatus(CourierStatus.AVAILABLE);
        userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent() && encoder.matches(password, userOptional.get().getPassword())) {
            User user = userOptional.get();
            Optional<String> token = jwtUtils.generateToken(username, user.getId());
            if (token.isPresent()) {
                UserDto userDto = UserMapper.mapUserEntityToUserDto(user);
                return LoginResponse.builder()
                        .token(token.get())
                        .user(userDto)
                        .build();
            }
        }
        throw new InvalidCredentialsException();
    }

    @Override
    public ValidateTokenResponse validateToken(String token) {
        Optional<String> usernameOptional = jwtUtils.validateTokenAndRetrieveUsername(token);
        if (usernameOptional.isPresent()) {
            String username = usernameOptional.get();
            Optional<User> user = userRepository.findByUsername(username);
            if (user.isPresent()) {
                return ValidateTokenResponse.builder()
                        .username(username)
                        .role(user.get().getRole())
                        .build();
            } else {
                throw new ProcessingTokenException();
            }
        }
        throw new TokenExpiredException();
    }

    private User validateAndPrepareUserEntityForSignUp(SignUpRequest request, Role role) {
        String username = request.getUsername();
        String password = encoder.encode(request.getPassword());
        Boolean userExists = userRepository.existsByUsername(username);
        if (userExists) {
            throw new UserExistsException("User with username " + username + " already exists");
        }
        return User.builder()
                .username(username)
                .role(role)
                .password(password)
                .build();
    }
}
