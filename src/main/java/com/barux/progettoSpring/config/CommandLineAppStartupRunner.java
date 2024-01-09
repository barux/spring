package com.barux.progettoSpring.config;

import com.barux.progettoSpring.user.Role;
import com.barux.progettoSpring.user.User;
import com.barux.progettoSpring.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    @Override
    public void run(String...args) throws Exception {
        createAdminUser();
        createDefaultUser();
    }

    private void createAdminUser() {
        var exists = userRepository.findByEmail("admin@barux.dev").isPresent();
        if (exists) {
            logger.info("Default admin user already exists");
            return;
        }
        User admin = User.builder()
                .firstName("admin")
                .lastName("admin")
                .email("admin@barux.dev")
                .password("Adm1n@")
                .role(Role.ADMIN)
                .isEnabled(true)
                .build();
        userRepository.save(admin);
        logger.info("Default admin user created");
    }

    private void createDefaultUser() {
        var exists = userRepository.findByEmail("test@barux.dev").isPresent();
        if (exists) {
            logger.info("Default user already exists");
            return;
        }
        User user = User.builder()
                .firstName("user")
                .lastName("user")
                .email("test@barux.dev")
                .password("T3st@")
                .role(Role.USER)
                .isEnabled(true)
                .build();
        userRepository.save(user);
        logger.info("Default user created");
    }
}
