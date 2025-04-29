package com.tfg.tfg_backend.controller;

import com.tfg.tfg_backend.model.User;
import com.tfg.tfg_backend.model.UserFavoriteLeague;
import com.tfg.tfg_backend.repository.UserFavoriteLeagueRepository;
import com.tfg.tfg_backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/favorites/leagues")
@CrossOrigin(origins = "*")
public class FavoriteLeagueController {
    
    private final UserFavoriteLeagueRepository userFavoriteLeagueRepository;
    private final UserRepository userRepository;

    public FavoriteLeagueController(UserFavoriteLeagueRepository userFavoriteLeagueRepository, UserRepository userRepository) {
        this.userFavoriteLeagueRepository = userFavoriteLeagueRepository;
        this.userRepository = userRepository;
    }

    // Endpoint para agregar una liga favorita
    @PostMapping("/add")
    public ResponseEntity<String> addFavoriteLeague(@RequestBody FavoriteLeagueRequest request) { //localhost:8080/api/favorites/leagues/add?userId=1&leagueId=12345
        Optional<User> userOpt = userRepository.findById(request.getUserId());
        if (!userOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
        UserFavoriteLeague favoriteLeague = new UserFavoriteLeague();
        favoriteLeague.setUser(userOpt.get());
        favoriteLeague.setLeagueId(request.getLeagueId());
        userFavoriteLeagueRepository.save(favoriteLeague);
        return ResponseEntity.ok("League added to favorites successfully!");
    }

    // Endpoint para eliminar una liga favorita
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFavoriteLeague(@RequestBody FavoriteLeagueRequest request) {
        // Supongamos que en el repositorio implementas este método para buscar la asociación
        UserFavoriteLeague favorite = userFavoriteLeagueRepository.findByUserIdAndLeagueId(request.getUserId(), request.getLeagueId());
        if(favorite == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Favorite league not found");
        }
        // Elimina el primer registro encontrado
        userFavoriteLeagueRepository.delete(favorite);
        return ResponseEntity.ok("Favorite league removed successfully!");
    }

    // Endpoint para listar las ligas favoritas de un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserFavoriteLeague>> getFavoriteLeagues(@PathVariable Long userId) { //localhost:8080/api/favorites/leagues/user/1
        List<UserFavoriteLeague> favorites = userFavoriteLeagueRepository.findByUserId(userId);
        return ResponseEntity.ok(favorites);
    }

    // DTO para la solicitud de agregar liga favorita
    public static class FavoriteLeagueRequest {
        private Long userId;
        private String leagueId;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getLeagueId() {
            return leagueId;
        }

        public void setLeagueId(String leagueId) {
            this.leagueId = leagueId;
        }
    }
}
