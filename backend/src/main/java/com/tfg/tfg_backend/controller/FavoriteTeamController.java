package com.tfg.tfg_backend.controller;

import com.tfg.tfg_backend.model.User;
import com.tfg.tfg_backend.model.UserFavoriteTeam;
import com.tfg.tfg_backend.repository.UserFavoriteTeamRepository;
import com.tfg.tfg_backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/favorites/teams")
@CrossOrigin(origins = "*")
public class FavoriteTeamController {

    private final UserFavoriteTeamRepository favoriteTeamRepository;
    private final UserRepository userRepository;

    public FavoriteTeamController(UserFavoriteTeamRepository favoriteTeamRepository, UserRepository userRepository) {
        this.favoriteTeamRepository = favoriteTeamRepository;
        this.userRepository = userRepository;
    }

    // Endpoint para agregar un equipo favorito
    @PostMapping("/add")
    public ResponseEntity<String> addFavoriteTeam(@RequestBody FavoriteTeamRequest request) {
        Optional<User> userOpt = userRepository.findById(request.getUserId());
        if (!userOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
        UserFavoriteTeam favoriteTeam = new UserFavoriteTeam();
        favoriteTeam.setUser(userOpt.get());
        favoriteTeam.setTeamId(request.getTeamId());
        favoriteTeamRepository.save(favoriteTeam);
        return ResponseEntity.ok("Team added to favorites successfully!");
    }

    // Endpoint para eliminar un equipo favorito
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFavoriteTeam(@RequestBody FavoriteTeamRequest request) {
        // Supongamos que en el repositorio implementas este método para buscar la asociación
        UserFavoriteTeam favorite = favoriteTeamRepository.findByUserIdAndTeamId(request.getUserId(), request.getTeamId());
        if (favorite == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Favorite team not found");
        }
        // Elimina el primer registro encontrado
        favoriteTeamRepository.delete(favorite);
        return ResponseEntity.ok("Favorite team removed successfully!");
        
    }

    // Endpoint para listar los equipos favoritos de un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserFavoriteTeam>> getFavoriteTeams(@PathVariable Long userId) {
        List<UserFavoriteTeam> favorites = favoriteTeamRepository.findByUserId(userId);
        return ResponseEntity.ok(favorites);
    }

    // DTO para la solicitud de agregar equipo favorito
    public static class FavoriteTeamRequest {
         private Long userId;
         private String teamId;

         public Long getUserId() {
              return userId;
         }
         public void setUserId(Long userId) {
              this.userId = userId;
         }
         public String getTeamId() {
              return teamId;
         }
         public void setTeamId(String teamId) {
              this.teamId = teamId;
         }
    }
}
