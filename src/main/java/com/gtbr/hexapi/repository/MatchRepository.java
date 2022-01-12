package com.gtbr.hexapi.repository;

import com.gtbr.hexapi.entity.Match;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MatchRepository extends CrudRepository<Match, UUID> {
}
