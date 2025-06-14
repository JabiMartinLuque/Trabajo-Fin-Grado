package com.tfg.tfg_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${prodFront.url}")
    private String resetLink;

    public void sendResetPasswordEmail(String to, String token, String username) {
        resetLink = resetLink + "/reset-password?token=" + token; // Ajusta la URL según corresponda
        String subject = "Reset Your Password";
        String body = "Hello " + username + ",\n\n"
            + "To reset your password, please click the following link:\n" 
            + resetLink + "\n\n"
            + "If you did not request a password reset, please ignore this message.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
