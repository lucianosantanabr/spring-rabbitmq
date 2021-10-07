package com.github.lucianosantanabr.repository;

import com.github.lucianosantanabr.domain.entity.FeatureFlag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureFlagRepository extends JpaRepository<FeatureFlag, String> {

}
