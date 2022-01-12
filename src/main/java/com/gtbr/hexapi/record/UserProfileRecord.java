package com.gtbr.hexapi.record;

import com.gtbr.hexapi.entity.UserProfile;
import com.gtbr.hexapi.entity.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserProfileRecord(
        UUID userUUID,
        Long shortId,
        String name,
        String email,
        String password,
        UserStatus userStatus,
        String deviceId,
        Long coin,
        LocalDateTime registeredAt
) {
}
