package com.barux.progettoSpring.auth.token;

import com.barux.progettoSpring.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VerificationToken {
    @Id
    @GeneratedValue
    private Integer id;
    private String token;
    private Date expiryDate = calculateExpiryDate();

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
    }

    static Date calculateExpiryDate() {
        var cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, 120);
        return new Date(cal.getTime().getTime());
    }
}
