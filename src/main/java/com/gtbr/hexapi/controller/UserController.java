package com.gtbr.hexapi.controller;

import com.gtbr.hexapi.entity.UserProfile;
import com.gtbr.hexapi.record.UserProfileRecord;
import com.gtbr.hexapi.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<List<UserProfile>> findAllUsers() {
        return ResponseEntity.ok(userProfileService.findAll());
    }

    @GetMapping("/short/{userShortId}")
    public ResponseEntity<UserProfile> findUserByUserShortId(@PathVariable Integer userShortId) {
        return ResponseEntity.ok(userProfileService.findUserById(userShortId));
    }

    @GetMapping("/{userUUID}")
    public ResponseEntity<UserProfile> findUserByUserUUID(@PathVariable UUID userUUID) {
        return ResponseEntity.ok(userProfileService.findUserById(userUUID));
    }

    @PostMapping
    public ResponseEntity<UserProfile> createUser(@RequestBody @Valid UserProfileRecord userProfileRecord) {
        return ResponseEntity.created(URI.create("")).body(userProfileService.createUser(userProfileRecord));
    }

    @PostMapping("/login")
    public ResponseEntity<UserProfile> login(@RequestBody @Valid UserProfileRecord userProfileRecord) {
        return ResponseEntity.created(URI.create("")).body(userProfileService.login(userProfileRecord));
    }

    @PatchMapping("/{userUUID}")
    public ResponseEntity<UserProfile> updateUser(@PathVariable UUID userUUID, @RequestBody UserProfileRecord userProfileRecord) {
        return ResponseEntity.accepted().body(userProfileService.updateUser(userUUID, userProfileRecord));
    }

    @DeleteMapping("/{userUUID}")
    public ResponseEntity<UserProfile> deleteUser(@PathVariable UUID userUUID) {
        return ResponseEntity.accepted().body(userProfileService.disableUser(userUUID));
    }
}
