package com.gtbr.hexapi.repository;

import com.gtbr.hexapi.entity.GameMode;
import com.gtbr.hexapi.entity.Match;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface MatchRepository extends CrudRepository<Match, UUID> {

    @Query("select m from Match m where m.userProfile.userUUID = :userId " +
            "and m.gameMode.name = :gameMode order by m.registeredAt DESC")
    List<Match> findByUserIdAndGameMode(UUID userId, String gameMode, Pageable pageable);

    @Query("select m from Match m where m.userProfile.userUUID = :userId " +
            "order by m.registeredAt DESC")
    List<Match> findByUserIdAnd(UUID userId, Pageable pageable);

    @Query(value = "select ma.* " +
            "from Match ma " +
            "where ma.match_uuid in (select distinct on (m.user_profile_id) m.match_uuid " +
            "                        from match m " +
            "                        where upper(m.game_mode_id) like upper(:gameMode) " +
            "                        order by m.user_profile_id, m.score DESC) order by ma.score DESC", nativeQuery = true)
    List<Match> findByGameModeOrderByScore(String gameMode, Pageable pageable);

    @Query("select m from Match m where m.userProfile.userUUID = :userId order by m.registeredAt DESC")
    List<Match> findLastByUserId(UUID userId, Pageable pageable);

    @Query("select m from Match m where m.userProfile.userUUID = :userId and m.gameMode = :gamemode order by m.score DESC")
    List<Match> findPersonalHighScores(UUID userId, GameMode gamemode);
}
