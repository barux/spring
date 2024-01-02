package com.barux.e4cSpring.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDTO> me() {
        return new ResponseEntity<>(userService.me(), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<UserDTO[]> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
}
