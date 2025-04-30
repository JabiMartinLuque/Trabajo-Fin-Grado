package com.tfg.tfg_backend.repository;

import com.tfg.tfg_backend.model.UserFavoriteTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserFavoriteTeamRepository extends JpaRepository<UserFavoriteTeam, Long> {
    List<UserFavoriteTeam> findByUserId(Long userId);
    UserFavoriteTeam findByUserIdAndTeamId(Long userId, String teamId);
}

