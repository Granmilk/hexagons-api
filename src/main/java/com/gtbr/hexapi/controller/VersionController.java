package com.gtbr.hexapi.controller;

import com.gtbr.hexapi.entity.Version;
import com.gtbr.hexapi.service.VersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/version")
public class VersionController {

    private final VersionService versionService;

    @GetMapping
    public ResponseEntity<Version> getActualVersion(){
        return ResponseEntity.ok(versionService.findActualVersion());
    }

}
