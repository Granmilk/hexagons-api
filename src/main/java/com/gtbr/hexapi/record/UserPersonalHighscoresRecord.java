package com.gtbr.hexapi.record;

import com.gtbr.hexapi.entity.Match;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record UserPersonalHighscoresRecord(UUID userId, Map<String, Integer> highscores) {

}
