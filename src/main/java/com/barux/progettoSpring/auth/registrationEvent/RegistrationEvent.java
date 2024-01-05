package com.barux.progettoSpring.auth.registrationEvent;

import com.barux.progettoSpring.user.UserDTO;
import lombok.*;
import org.springframework.context.ApplicationEvent;

@Builder
@Getter
@Setter
public class RegistrationEvent extends ApplicationEvent {
    private UserDTO user;
    private String appUrl;

    public RegistrationEvent(UserDTO user, String appUrl) {
        super(user);
        this.user = user;
        this.appUrl = appUrl;
    }
}
