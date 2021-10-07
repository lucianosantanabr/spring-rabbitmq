package com.github.lucianosantanabr.service;

import com.github.lucianosantanabr.domain.entity.FeatureFlag;
import com.github.lucianosantanabr.repository.FeatureFlagRepository;
import java.util.Date;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@CacheConfig(cacheNames = {FeatureFlagService.CACHE})
public class FeatureFlagService {

  public static final String CACHE = "features";

  private @Autowired
  FeatureFlagRepository repo;


  @Cacheable(CACHE)
  public FeatureFlag get(String id) {
    log.debug("Reading feature {} from database...", id);
    Optional<FeatureFlag> read = read(id);
    if (read.isPresent()) {
      log.debug("Feature {} found on database...", id);
      return read.get();
    }
    log.debug("Returning MOCKED feature as status=true...");
    return FeatureFlag //
        .builder() //
        .id(id) //
        .status(true) //
        .build();
  }


  @Transactional(readOnly = true)
  public Optional<FeatureFlag> read(String id) {
    return repo.findById(id);
  }

  @Transactional
  public FeatureFlag active(String id) {
    return add(id, true);
  }

  @Transactional
  public FeatureFlag inactive(String id) {
    return add(id, false);
  }

  @Transactional
  public void remove(String id) {
    log.debug("Deleating feature {} from database...", id);
    repo.deleteById(id);
    repo.flush();
  }

  @Transactional
  public FeatureFlag add(String id, Boolean status) {
    FeatureFlag feature = null;
    Optional<FeatureFlag> expected = repo.findById(id);
    if (expected.isPresent()) {
      log.debug("Updating feature {} on database with status...", id, status);
      feature = expected.get();
    } else {
      log.debug("Adding feature {} on database with status...", id, status);
      feature = new FeatureFlag();
      feature.setId(id);
      feature.setCreatedAt(new Date());
      feature.setActivatedAt(new Date());
    }
    feature.setStatus(status);

    if(status) {
      feature.setActivatedAt(new Date());
      feature.setInactivatedAt(null);
    } else {
      feature.setInactivatedAt(new Date());
    }

    return repo.saveAndFlush(feature);
  }

  @CacheEvict(value = CACHE)
  public void clearCache(String id) {
    log.debug("Cleaning cache for feature {}...", id);
  }

  @CacheEvict(value = CACHE, allEntries = true)
  public void clearCache() {
    log.debug("Cleaning cache for all features...");
  }
}
