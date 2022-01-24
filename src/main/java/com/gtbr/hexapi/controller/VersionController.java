package com.gtbr.hexapi.controller;

import com.gtbr.hexapi.entity.Version;
import com.gtbr.hexapi.service.VersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/version")
public class VersionController {

    private final VersionService versionService;

    @GetMapping
    public ResponseEntity<Version> getActualVersion(){
        return ResponseEntity.ok(versionService.findActualVersion());
    }

    @PatchMapping
    public ResponseEntity<Version> updateVersion(@RequestBody Map<String, Boolean> versionParameter) {
        return ResponseEntity.ok(versionService.updateVersion(versionParameter));
    }
}
