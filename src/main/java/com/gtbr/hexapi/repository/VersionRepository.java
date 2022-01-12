package com.gtbr.hexapi.repository;

import com.gtbr.hexapi.entity.Version;
import org.springframework.data.repository.CrudRepository;

public interface VersionRepository extends CrudRepository<Version, String> {
}
