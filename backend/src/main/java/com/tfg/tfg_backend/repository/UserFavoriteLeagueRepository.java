package com.tfg.tfg_backend.repository;

import com.tfg.tfg_backend.model.User;
import com.tfg.tfg_backend.model.UserFavoriteLeague;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserFavoriteLeagueRepository extends JpaRepository<UserFavoriteLeague, Long> {
    List<UserFavoriteLeague> findByUserId(Long userId);
    UserFavoriteLeague findByUserIdAndLeagueId(Long userId, String leagueId);
}
