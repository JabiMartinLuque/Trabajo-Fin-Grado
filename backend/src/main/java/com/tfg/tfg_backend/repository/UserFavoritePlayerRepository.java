package com.tfg.tfg_backend.repository;

import com.tfg.tfg_backend.model.UserFavoritePlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserFavoritePlayerRepository extends JpaRepository<UserFavoritePlayer, Long> {
    List<UserFavoritePlayer> findByUserId(Long userId);
}

