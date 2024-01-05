package com.barux.progettoSpring.auth.token;

import com.barux.progettoSpring.user.User;
import com.barux.progettoSpring.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    public void saveToken(VerificationToken token) {
        tokenRepository.save(token);
    }

    public ResponseEntity<String> confirmRegistration(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken.getUser().isEnabled()) {
            return new ResponseEntity<>("User already enabled", HttpStatus.BAD_REQUEST);
        }
        if (verificationToken.getExpiryDate().getTime() - System.currentTimeMillis() <= 0) {
            return new ResponseEntity<>("Token expired", HttpStatus.BAD_REQUEST);
        }
        User user = verificationToken.getUser();
        user.setIsEnabled(true);
        userRepository.save(user);
        tokenRepository.delete(verificationToken);
        return new ResponseEntity<>("User enabled", HttpStatus.OK);
    }
}
