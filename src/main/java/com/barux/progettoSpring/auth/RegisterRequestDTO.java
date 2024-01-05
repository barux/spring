package com.barux.progettoSpring.auth;

import com.barux.progettoSpring.user.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {

    @NotBlank(message = "firstName can't be blank")
    @NotNull(message = "firstName is required")
    private String firstName;

    @NotBlank(message = "lastName can't be blank")
    @NotNull(message = "lastName is required")
    private String lastName;

    @NotBlank(message = "email can't be blank")
    @NotNull(message = "email is required")
    @Email(message = "email is not valid")
    private String email;

    @NotBlank(message = "password can't be blank")
    @NotNull(message = "password is required")
    @Transient
    @Size(min = 8, message = "password must be at least 8 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",
            message = "password must contain at least one uppercase letter, one lowercase letter, and one number")
    private String password;

    private Role role;
}
