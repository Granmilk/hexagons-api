package com.gtbr.hexapi.service;

import com.gtbr.hexapi.entity.Match;
import com.gtbr.hexapi.enums.Operator;
import com.gtbr.hexapi.exception.InvalidMatchException;
import com.gtbr.hexapi.exception.ObjectNotFoundException;
import com.gtbr.hexapi.record.MatchRecord;
import com.gtbr.hexapi.record.MatchScoreBoardRecord;
import com.gtbr.hexapi.record.UserPersonalHighscoresRecord;
import com.gtbr.hexapi.repository.GameModeRepository;
import com.gtbr.hexapi.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final UserProfileService userProfileService;
    private final GameModeRepository gameMode;

    public Match findById(UUID uuid) {
        return matchRepository.findById(uuid).orElseThrow(() -> {
            throw new ObjectNotFoundException("Match not found", HttpStatus.NOT_FOUND);
        });
    }

    public List<Match> findByUserId(UUID userId, String gamemode, Integer page, Integer pageSize) {
        return Objects.isNull(gamemode)
                ? matchRepository.findByUserIdAnd(userId, PageRequest.of(page, pageSize))
                : matchRepository.findByUserIdAndGameMode(userId, gamemode, PageRequest.of(page, pageSize));
    }

    public Match registerMatch(MatchRecord matchRecord) {
        if (Boolean.FALSE.equals(matchRecord.isValid())) throw new InvalidMatchException();
        Match match = matchRepository.save(matchRecord.toCreateEntity());
        userProfileService.updateCoins(matchRecord.userProfile().getUserUUID(), Operator.PLUS, Boolean.TRUE.equals(match.getWatchedAd()) ? match.getCoin() * 2 : match.getCoin());
        return match;
    }

    public List<MatchScoreBoardRecord> findMatchList(String gameMode, Integer size) {
        return matchRepository.findByGameModeOrderByScore(gameMode, Pageable.ofSize(size)).stream()
                .map(MatchScoreBoardRecord::from)
                .sorted(Comparator.comparingInt(MatchScoreBoardRecord::score).reversed())
                .toList();
    }

    public Match watchedAd(UUID userId) {
        Match match = matchRepository.findLastByUserId(userId, Pageable.ofSize(1)).get(0);
        if (Boolean.FALSE.equals(match.getWatchedAd())) {
            match.setWatchedAd(true);
            matchRepository.save(match);
            userProfileService.updateCoins(match.getUserProfile().getUserUUID(), Operator.PLUS, match.getCoin());

            return match;
        }
        throw new InvalidMatchException();
    }

    public UserPersonalHighscoresRecord findUserPersonalHighscore(UUID userId) {
        Map<String, Integer> highScoresMap = new HashMap<>();
        gameMode.findAll().forEach(gamemode -> {
            List<Match> personalHighScores = matchRepository.findPersonalHighScores(userId, gamemode);
            if (personalHighScores.isEmpty())
                highScoresMap.put(gamemode.getName().toLowerCase(), 0);
            else
                highScoresMap.put(gamemode.getName().toLowerCase(), personalHighScores.get(0).getScore().intValue());
        });

        return new UserPersonalHighscoresRecord(userId, highScoresMap);
    }

}
