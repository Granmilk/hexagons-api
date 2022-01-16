package com.gtbr.hexapi.record;

import com.gtbr.hexapi.entity.GameMode;
import com.gtbr.hexapi.entity.Match;
import com.gtbr.hexapi.entity.UserProfile;
import com.gtbr.hexapi.util.Validator;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public record MatchRecord(UUID matchUUID,
                          Long matchShortId,
                          UserProfile userProfile,
                          GameMode gameMode,
                          Long score,
                          LocalDateTime registeredAt,
                          Long coin,
                          Integer difficulty,
                          Boolean watchedAd,
                          String validator) {

    public Boolean isValid() {
        return Objects.equals(validator, Validator.createMatchValidator(this.userProfile.getUserUUID(), this.coin, this.score, this.gameMode.getName(), this.difficulty));
    }

    public Match toCreateEntity() {
        return Match.builder()
                .coin(this.coin)
                .difficulty(this.difficulty)
                .gameMode(this.gameMode)
                .score(this.score)
                .userProfile(this.userProfile)
                .watchedAd(this.watchedAd)
                .build();
    }

}
