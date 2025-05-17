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

    @Value("${prod.url}")
    private String resetLink;

    public void sendResetPasswordEmail(String to, String token, String username) {
        resetLink = resetLink + "/reset-password?token=" + token; // Ajusta la URL según corresponda
        String subject = "Restablecimiento de contraseña";
        String body = "Hola " + username + ",\n\n"
                + "Para restablecer tu contraseña, haz clic en el siguiente enlace:\n" 
                + resetLink + "\n\n" 
                + "Si no solicitaste el restablecimiento, ignora este mensaje.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
