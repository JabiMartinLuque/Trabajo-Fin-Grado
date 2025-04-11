package com.tfg.tfg_backend.controller;

import com.tfg.tfg_backend.model.User;
import com.tfg.tfg_backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Endpoint para obtener el perfil de un usuario usando la entidad User
     * Ejemplo de URL: http://localhost:8080/api/users/profile?userId=1
     * 
     * @param userId El identificador del usuario
     * @return Objeto User en caso de encontrarlo o 404 si no existe
     */
    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@RequestParam("userId") Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(userOpt.get());
    }
}

