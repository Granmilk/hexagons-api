package com.gtbr.hexapi.repository;

import com.gtbr.hexapi.entity.UserItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface UserItemRepository extends CrudRepository<UserItem, Long> {
    @Query("select ui from UserItem ui where ui.userProfile.userUUID = :userId")
    List<UserItem> findByUserId(UUID userId);
}
