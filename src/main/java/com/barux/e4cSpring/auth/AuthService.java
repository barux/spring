package com.barux.e4cSpring.auth;

import com.barux.e4cSpring.config.JwtService;
import com.barux.e4cSpring.user.Role;
import com.barux.e4cSpring.user.User;
import com.barux.e4cSpring.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public RegisterResponse register(RegisterRequest request) {
        // check if user with that mail already exists
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            // TODO cambiare RuntimeException con una classe custom o con una response generica che comprenda sia data che errori
            throw new RuntimeException("User with that email already exists");
        }
        var role = request.getRole() != null ? request.getRole() : Role.USER;
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();
        repository.save(user);
        return RegisterResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var claims = jwtService.generateClaims(user);
        var jwtToken = jwtService.generateToken(claims, user);
        return LoginResponse.builder()
                .user(RegisterResponse.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build())
                .token(jwtToken)
                .build();
    }
}
