package com.barux.progettoSpring.auth;

import com.barux.progettoSpring.config.JwtService;
import com.barux.progettoSpring.config.Mapper;
import com.barux.progettoSpring.exception.ValidationException;
import com.barux.progettoSpring.user.UserDTO;
import com.barux.progettoSpring.user.Role;
import com.barux.progettoSpring.user.User;
import com.barux.progettoSpring.user.UserRepository;
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
    private final Mapper<User, UserDTO> userMapper;
    public UserDTO register(RegisterRequestDTO request) {
        // check if user with that mail already exists
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new ValidationException("User with that email already exists");
        }
        var role = request.getRole() != null ? request.getRole() : Role.USER;
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();
        var registeredUser = repository.save(user);
        return userMapper.mapTo(registeredUser);
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ValidationException("User not found"));
        var claims = jwtService.generateClaims(user);
        var jwtToken = jwtService.generateToken(claims, user);
        return LoginResponseDTO.builder()
                .user(userMapper.mapTo(user))
                .token(jwtToken)
                .build();
    }
}
