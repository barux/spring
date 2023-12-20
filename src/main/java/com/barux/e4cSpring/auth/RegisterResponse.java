package com.barux.e4cSpring.auth;

import com.barux.e4cSpring.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
