package com.gtbr.hexapi.repository;

import com.gtbr.hexapi.entity.Match;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface MatchRepository extends CrudRepository<Match, UUID> {

    @Query("select m from Match m where m.userProfile.userUUID = :userId " +
            "and m.gameMode.name = :gameMode order by m.registeredAt DESC")
    List<Match> findByUserId(UUID userId, Pageable pageable);

    @Query("select m from Match m where " +
            "m.gameMode.name = :gameMode order by m.score DESC")
    List<Match> findByGameModeOrderByScore(String gameMode, Pageable pageable);

    @Query("select m from Match m where m.userProfile.userUUID = :userId order by m.registeredAt DESC")
    Match findLastByUserId(UUID userId, Pageable pageable);
}
