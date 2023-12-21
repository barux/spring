package com.barux.e4cSpring.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @NotBlank(message = "email can't be blank")
    @NotNull(message = "email is required")
    private String email;

    @NotBlank(message = "password can't be blank")
    @NotNull(message = "password is required")
    String password;
}
