package com.tfg.tfg_backend.controller;

import com.tfg.tfg_backend.model.ResetToken;
import com.tfg.tfg_backend.model.User;
import com.tfg.tfg_backend.repository.UserRepository;
import com.tfg.tfg_backend.security.JwtUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.tfg.tfg_backend.service.EmailService;
import com.tfg.tfg_backend.repository.ResetTokenRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final ResetTokenRepository resetTokenRepository;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, EmailService emailService, ResetTokenRepository resetTokenRepository) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.resetTokenRepository = resetTokenRepository;
    }

    @PostMapping("/register") //POST 
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody Map<String, String> request) {
        User user = userRepository.findByUsername(request.get("username"))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario no encontrado"));

        if (!passwordEncoder.matches(request.get("password"), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña inválida");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        Map<String,Object> resp = new HashMap<>();
        resp.put("token", token);
        resp.put("userId", user.getId());
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email"); // se lee la propiedad "email"
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario no encontrado"));
        
        // Generar token de restablecimiento
        String token = UUID.randomUUID().toString();
        LocalDateTime expiryDate = LocalDateTime.now().plusHours(1); // por ejemplo, 1 hora de validez
        
        // Guardar el token asociado al usuario
        ResetToken resetToken = new ResetToken(token, user, expiryDate);
        resetTokenRepository.save(resetToken);
        
        try {
            // Enviar correo de restablecimiento
            emailService.sendResetPasswordEmail(user.getEmail(), token, user.getUsername());
        } catch(Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al enviar el correo de restablecimiento");
        }
        
        return ResponseEntity.ok("Correo de restablecimiento de contraseña enviado a " + email);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
        String tokenStr = request.get("token");
        String newPassword = request.get("newPassword");
        
        // Buscar el token en el almacenamiento
        ResetToken resetToken = resetTokenRepository.findByToken(tokenStr)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token inválido"));
        
        // Comprobar la expiración
        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token expirado");
        }
        
        // Obtener el usuario asociado y actualizar su contraseña
        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        
        // Eliminar el token para que ya no se pueda reutilizar
        resetTokenRepository.delete(resetToken);
        
        return ResponseEntity.ok("Contraseña actualizada con éxito");
    }

}
