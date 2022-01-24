package com.gtbr.hexapi.service;

import com.gtbr.hexapi.entity.Version;
import com.gtbr.hexapi.repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VersionService {

    private final VersionRepository versionRepository;

    public Version findActualVersion() {
        return versionRepository.findActualVersion();
    }

    public Version updateVersion(Map<String, Boolean> versionParameter) {
        Version actualVersion = findActualVersion();
        List<Integer> numberList = Arrays
                .stream(actualVersion.getPlaceholder().split("\\."))
                .map(Integer::valueOf)
                .toList();

        Integer[] numbers = new Integer[3];
        if (versionParameter.containsKey("Feature") && Boolean.TRUE.equals(versionParameter.get("Feature")))
            numbers[1] = numberList.get(1) + 1;
        else
            numbers[1] = numberList.get(1);

        if (versionParameter.containsKey("Hotfix") && Boolean.TRUE.equals(versionParameter.get("Hotfix")))
            numbers[2] = numberList.get(2) + 1;
        else
            numbers[2] = numberList.get(2);

        if (versionParameter.containsKey("Major") && Boolean.TRUE.equals(versionParameter.get("Major"))) {
            numbers[0] = numberList.get(0) + 1;
            numbers[1] = 0;
            numbers[2] = 0;
        }
        else
            numbers[0] = numberList.get(0);

        actualVersion.setEndedAt(LocalDateTime.now());
        versionRepository.save(actualVersion);
        return versionRepository.save(Version.builder().placeholder(numbers[0] + "." + numbers[1] + "." + numbers[2]).build());
    }
}
