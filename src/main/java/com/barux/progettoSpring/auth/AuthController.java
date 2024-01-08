package com.barux.progettoSpring.auth;

import com.barux.progettoSpring.auth.registrationEvent.RegistrationEvent;
import com.barux.progettoSpring.auth.token.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {
    private final AuthService authService;
    private final ApplicationEventPublisher eventPublisher;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<String> register (
        @Valid @RequestBody RegisterRequestDTO request, final HttpServletRequest req
    ) {
        var user = authService.register(request);
        var appUrl = "http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
        eventPublisher.publishEvent(new RegistrationEvent(user, appUrl));
        return new ResponseEntity<>("Success, check your email to activate " +
                "your account and complete the registration", HttpStatus.OK);
    }

    @GetMapping("/registrationConfirm")
    public ResponseEntity<String> confirmRegistration (
        @RequestParam("token") String token
    ) {
        return tokenService.confirmRegistration(token);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (
        @Valid @RequestBody LoginRequestDTO request
    ) {
        return new ResponseEntity<>(authService.login(request), HttpStatus.OK);
    }
}
