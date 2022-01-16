package com.gtbr.hexapi.record;

import com.gtbr.hexapi.entity.Match;
import com.gtbr.hexapi.util.Constants;

import java.time.format.DateTimeFormatter;

public record MatchScoreBoardRecord(String username, String gameMode, Integer score, Integer difficulty, String registeredAt) {

    public static MatchScoreBoardRecord from(Match match) {
        return new MatchScoreBoardRecord(match.getUserProfile().getName(), match.getGameMode().getName(), match.getScore().intValue(), match.getDifficulty(), match.getRegisteredAt().format(DateTimeFormatter.ofPattern(Constants.DATE_PATTERN)));
    }
}
