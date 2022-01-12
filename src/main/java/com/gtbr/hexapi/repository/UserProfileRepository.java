package com.gtbr.hexapi.repository;

import com.gtbr.hexapi.entity.UserProfile;
import com.gtbr.hexapi.record.UserProfileRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, UUID> {

    Optional<UserProfile> findByShortId(Integer shortId);

    default UserProfile update(UserProfileRecord userProfileRecord){


        return null;
    }
}
