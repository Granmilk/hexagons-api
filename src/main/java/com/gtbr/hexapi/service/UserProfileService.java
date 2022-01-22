package com.gtbr.hexapi.service;

import com.gtbr.hexapi.entity.UserProfile;
import com.gtbr.hexapi.entity.enums.UserStatus;
import com.gtbr.hexapi.enums.Operator;
import com.gtbr.hexapi.exception.ObjectNotFoundException;
import com.gtbr.hexapi.exception.UserAlreadyExistsException;
import com.gtbr.hexapi.record.UserProfileRecord;
import com.gtbr.hexapi.repository.UserProfileRepository;
import com.gtbr.hexapi.util.QueryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<UserProfile> findAll() {
        return (List<UserProfile>) userProfileRepository.findAll();
    }

    public UserProfile findUserById(Integer userShortId) {
        return userProfileRepository.findByShortId(userShortId)
                .orElseThrow(() -> {
                    throw new ObjectNotFoundException("user not found", HttpStatus.NOT_FOUND);
                });
    }

    public UserProfile findUserById(UUID userUUID) {
        return userProfileRepository.findById(userUUID)
                .orElseThrow(() -> {
                    throw new ObjectNotFoundException("user not found", HttpStatus.NOT_FOUND);
                });
    }

    public UserProfile createUser(UserProfileRecord userProfileRecord) {
        if (userProfileRepository.findByEmail(userProfileRecord.email()).isPresent())
            throw new UserAlreadyExistsException();

        UserProfile user = userProfileRepository.save(UserProfile.builder()
                .email(userProfileRecord.email())
                .name(userProfileRecord.name())
                .password(bCryptPasswordEncoder.encode(userProfileRecord.password()))
                .deviceId(userProfileRecord.deviceId())
                .build());

        return findUserById(user.getUserUUID());
    }

    public UserProfile updateUser(UUID userUUID, UserProfileRecord userProfileRecord) {
        UserProfile user = findUserById(userUUID);

        return userProfileRepository.save(merge(user, userProfileRecord));
    }

    private UserProfile merge(UserProfile user, UserProfileRecord userProfileRecord) {
        Map<String, Object> mapFromFilled = QueryUtils.getMapFromFilled(userProfileRecord);

        mapFromFilled.forEach((name, value) -> {
            if (name.equalsIgnoreCase("name")){
                user.setName((String) value);
            }
            if (name.equalsIgnoreCase("email")){
                user.setEmail((String) value);
            }
            if (name.equalsIgnoreCase("password")){
                user.setPassword(bCryptPasswordEncoder.encode((String) value));
            }
        });

        return user;
    }

    public UserProfile disableUser(UUID userUUID) {
        UserProfile user = findUserById(userUUID);

        user.setUserStatus(UserStatus.DISABLED);
        return userProfileRepository.save(user);
    }

    public void updateCoins(UUID userId, Operator operator , Long coins) {
        UserProfile user = findUserById(userId);
        var value = Objects.equals(operator, Operator.MINUS)
                ? user.getCoin()-coins
                : user.getCoin()+coins;

        user.setCoin(value);
        userProfileRepository.save(user);
    }
}
