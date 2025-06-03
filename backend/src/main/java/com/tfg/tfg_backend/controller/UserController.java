package com.tfg.tfg_backend.controller;

import com.tfg.tfg_backend.model.User;
import com.tfg.tfg_backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;

    private final String uploadDir = "uploads";

    @Value("${prod.url}")
    private String prodUrl;

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

    @PostMapping("/{userId}/profile-image")
    public ResponseEntity<String> uploadProfileImage(
            @PathVariable Long userId,
            @RequestParam("file") MultipartFile file) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("El archivo está vacío");
        }
        try {
            // Asegurarse de que existe el directorio de uploads
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            // Generar un nombre único para el archivo
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String newFileName = "user-" + userId + "-" + System.currentTimeMillis() + fileExtension;
            Path filePath = uploadPath.resolve(newFileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Por ejemplo, actualizamos la URL de la imagen del usuario.
            // Se asume que el directorio "uploads" se servirá como recurso estático.
            User user = userOpt.get();
            String imageUrl = prodUrl + "/uploads/" + newFileName;            
            user.setProfileImageUrl(imageUrl);
            userRepository.save(user);

            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el archivo.");
        }
    }

    @DeleteMapping("/{userId}/profile-image")
    public ResponseEntity<?> deleteProfileImage(@PathVariable Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        User user = userOpt.get();
        String imageUrl = user.getProfileImageUrl();
        if (imageUrl == null || imageUrl.isEmpty()) {
            return ResponseEntity.badRequest().body("El usuario no tiene imagen de perfil");
        }
        
        // Extraer el nombre del archivo de la URL (se asume que la URL contiene "/uploads/")
        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        Path filePath = Paths.get(uploadDir).resolve(fileName);
        
        try {
            Files.deleteIfExists(filePath);
            // Se actualiza el usuario para quitar la URL de la imagen de perfil
            user.setProfileImageUrl(null);
            userRepository.save(user);
            return ResponseEntity.ok("Imagen eliminada exitosamente");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la imagen");
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long userId, @RequestBody User updatedUser) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        User user = userOpt.get();
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        // Puedes actualizar otros campos si lo deseas
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }
}

