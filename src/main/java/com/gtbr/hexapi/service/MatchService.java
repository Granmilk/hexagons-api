package com.gtbr.hexapi.service;

import com.gtbr.hexapi.entity.Match;
import com.gtbr.hexapi.exception.InvalidMatchException;
import com.gtbr.hexapi.exception.ObjectNotFoundException;
import com.gtbr.hexapi.record.MatchRecord;
import com.gtbr.hexapi.record.MatchScoreBoardRecord;
import com.gtbr.hexapi.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final UserProfileService userProfileService;

    public Match findById(UUID uuid) {
        return matchRepository.findById(uuid).orElseThrow(() -> {
            throw new ObjectNotFoundException("Match not found", HttpStatus.NOT_FOUND);
        });
    }

    public List<Match> findByUserId(UUID userId, String gamemode, Integer page, Integer pageSize) {
        return matchRepository.findByUserId(userId, gamemode, PageRequest.of(page, pageSize));
    }

    public Match registerMatch(MatchRecord matchRecord) {
        if (Boolean.FALSE.equals(matchRecord.isValid())) throw new InvalidMatchException();
        Match match = matchRepository.save(matchRecord.toCreateEntity());
        userProfileService.addCoins(matchRecord.userProfile().getUserUUID(), Boolean.TRUE.equals(match.getWatchedAd()) ? match.getCoin() * 2 : match.getCoin());
        return match;
    }

    public List<MatchScoreBoardRecord> findMatchList(String gameMode, Integer size) {
        return matchRepository.findByGameModeOrderByScore(gameMode, Pageable.ofSize(size)).stream()
                .map(MatchScoreBoardRecord::from)
                .toList();
    }

    public Match watchedAd(UUID userId) {
        Match match = matchRepository.findLastByUserId(userId, Pageable.ofSize(1));
        if (Boolean.FALSE.equals(match.getWatchedAd())) {
            match.setWatchedAd(true);
            matchRepository.save(match);
            userProfileService.addCoins(match.getUserProfile().getUserUUID(), match.getCoin());
        }
        throw new InvalidMatchException();
    }
}
