package com.barux.progettoSpring.auth;

import com.barux.progettoSpring.user.UserDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register (
        @Valid @RequestBody RegisterRequestDTO request
    ) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (
        @Valid @RequestBody LoginRequestDTO request
    ) {
        return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
    }
}
