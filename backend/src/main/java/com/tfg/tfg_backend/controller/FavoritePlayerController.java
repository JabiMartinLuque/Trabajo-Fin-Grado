package com.tfg.tfg_backend.controller;

import com.tfg.tfg_backend.model.User;
import com.tfg.tfg_backend.model.UserFavoritePlayer;
import com.tfg.tfg_backend.repository.UserFavoritePlayerRepository;
import com.tfg.tfg_backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/favorites/players")
@CrossOrigin(origins = "*")
public class FavoritePlayerController {

    private final UserFavoritePlayerRepository favoritePlayerRepository;
    private final UserRepository userRepository;

    public FavoritePlayerController(UserFavoritePlayerRepository favoritePlayerRepository, UserRepository userRepository) {
        this.favoritePlayerRepository = favoritePlayerRepository;
        this.userRepository = userRepository;
    }

    // Endpoint para agregar un jugador favorito
    @PostMapping("/add")
    public ResponseEntity<String> addFavoritePlayer(@RequestBody FavoritePlayerRequest request) { //localhost:8080/api/favorites/players/add?userId=1&playerId=12345
        Optional<User> userOpt = userRepository.findById(request.getUserId());
        if (!userOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
        UserFavoritePlayer favoritePlayer = new UserFavoritePlayer();
        favoritePlayer.setUser(userOpt.get());
        favoritePlayer.setPlayerId(request.getPlayerId());
        favoritePlayerRepository.save(favoritePlayer);
        return ResponseEntity.ok("Player added to favorites successfully!");
    }

    // Endpoint para eliminar un jugador favorito
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFavoritePlayer(@RequestBody FavoritePlayerRequest request) {
        // Supongamos que en el repositorio implementas este método para buscar la asociación
        UserFavoritePlayer favorite = favoritePlayerRepository.findByUserIdAndPlayerId(request.getUserId(), request.getPlayerId());
        if (favorite == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Favorite player not found");
        }
        // Elimina el primer registro encontrado
        favoritePlayerRepository.delete(favorite);
        return ResponseEntity.ok("Favorite player removed successfully!");
        
    }

    // Endpoint para listar los jugadores favoritos de un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserFavoritePlayer>> getFavoritePlayers(@PathVariable Long userId) { //localhost:8080/api/favorites/players/user/1
        List<UserFavoritePlayer> favorites = favoritePlayerRepository.findByUserId(userId);
        return ResponseEntity.ok(favorites);
    }

    // DTO para la solicitud de agregar jugador favorito
    public static class FavoritePlayerRequest {
         private Long userId;
         private String playerId;

         public Long getUserId() {
              return userId;
         }
         public void setUserId(Long userId) {
              this.userId = userId;
         }
         public String getPlayerId() {
              return playerId;
         }
         public void setPlayerId(String playerId) {
              this.playerId = playerId;
         }
    }
}
