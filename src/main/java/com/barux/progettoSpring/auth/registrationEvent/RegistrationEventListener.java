package com.barux.progettoSpring.auth.registrationEvent;

import com.barux.progettoSpring.auth.registrationEvent.RegistrationEvent;
import com.barux.progettoSpring.auth.token.TokenService;
import com.barux.progettoSpring.auth.token.VerificationToken;
import com.barux.progettoSpring.config.Mapper;
import com.barux.progettoSpring.user.User;
import com.barux.progettoSpring.user.UserDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationEventListener implements ApplicationListener<RegistrationEvent> {
    private final TokenService tokenService;
    private final Mapper<User, UserDTO> mapper;
    private final JavaMailSender mailSender;

    @Value("${emailOutlook}")
    private String email;

    @Override
    public void onApplicationEvent(RegistrationEvent event) {
        UserDTO user = event.getUser();
        String verificationToken = UUID.randomUUID().toString();
        tokenService.saveToken(new VerificationToken(verificationToken, mapper.mapFrom(user)));
        String url = event.getAppUrl() + "/auth/registrationConfirm?token=" + verificationToken;
        try {
            sendVerificationEmail(url, user.getEmail());
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendVerificationEmail(String url, String userMail) throws MessagingException, UnsupportedEncodingException {
        String subject = "Registration Confirmation";
        String senderName = "Progetto Spring";
        String mailContent = "Please click the link below to activate your account: \n" + url;
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom(email, senderName);
        messageHelper.setTo(userMail);
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent);
        mailSender.send(message);
    }
}
