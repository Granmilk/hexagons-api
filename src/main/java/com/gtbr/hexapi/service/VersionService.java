package com.gtbr.hexapi.service;

import com.gtbr.hexapi.entity.Version;
import com.gtbr.hexapi.repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VersionService {

    private final VersionRepository versionRepository;

    public Version findActualVersion(){
        return versionRepository.findActualVersion();
    }
}
