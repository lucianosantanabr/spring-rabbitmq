package com.github.lucianosantanabr.resource;

import com.github.lucianosantanabr.commons.ResponseUtility;
import com.github.lucianosantanabr.commons.StringUtility;
import com.github.lucianosantanabr.domain.entity.FeatureFlag;
import com.github.lucianosantanabr.service.FeatureFlagService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/feature", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiOperation(value = "", tags = "Features")
public class FeatureFlagResource implements StringUtility, ResponseUtility {

  private @Autowired
  FeatureFlagService service;

  @ApiOperation(value = "Get feature status or NO CONTENT", tags = "Features")
  @ApiResponses(value = { //
      @ApiResponse(code = 200, message = "Feature status returned"), //
      @ApiResponse(code = 204, message = "Feature not found"), //
      @ApiResponse(code = 500, message = "Internal server error") //
  })
  @GetMapping(path = "/{id}/exist")
  public ResponseEntity<FeatureFlag> get(@PathVariable("id") String id) {
    log.info("Receiving GET /exist Feature status call");
    Optional<FeatureFlag> expected = service.read(id);
    if (expected.isPresent()) {
      log.info("Feature {} found", id);
      return ok(expected.get());
    }
    log.info("Feature {} not found", id);
    return noContent();
  }

  @ApiOperation(value = "Get feature status or TRUE", tags = "Features")
  @ApiResponses(value = { //
      @ApiResponse(code = 200, message = "Feature status returned or TRUE"), //
      @ApiResponse(code = 500, message = "Internal server error") //
  })
  @GetMapping(path = "/{id}")
  public ResponseEntity<FeatureFlag> patch(@PathVariable("id") String id) {
    log.info("Receiving GET Feature status call");
    return ok(service.get(id));
  }

  @ApiOperation(value = "Add or Update a Feature", tags = "Features")
  @ApiResponses(value = { //
      @ApiResponse(code = 200, message = "Feature added"), //
      @ApiResponse(code = 500, message = "Internal server error") //
  })
  @PostMapping(path = "/{id}/{status}")
  public ResponseEntity<FeatureFlag> post( //
      @PathVariable("id") String id, //
      @PathVariable("status") Boolean status) {
    log.info("Receiving feature add call");
    return ok(service.add(id, status));
  }

  @ApiOperation(value = "Remove a feature", tags = "Features")
  @ApiResponses(value = { //
      @ApiResponse(code = 200, message = "Feature removed"), //
      @ApiResponse(code = 204, message = "Feature not found"), //
      @ApiResponse(code = 500, message = "Internal server error") //
  })
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<FeatureFlag> deleteCache(@PathVariable("id") String id) {
    Optional<FeatureFlag> expected = service.read(id);
    if (expected.isPresent()) {
      log.info("Feature {} found", id);
      FeatureFlag featureFlag = expected.get();
      service.remove(featureFlag.getId());
      return ok();
    }
    log.info("Feature {} not found", id);
    return noContent();
  }

  @ApiOperation(value = "Clear cache for a feature", tags = "Features")
  @ApiResponses(value = { //
      @ApiResponse(code = 200, message = "Feature cache cleaned"), //
      @ApiResponse(code = 500, message = "Internal server error") //
  })
  @DeleteMapping(path = "/{id}/cache")
  public ResponseEntity<Void> clearCache(@PathVariable("id") String id) {
    service.clearCache(id);
    return ok();
  }

  @ApiOperation(value = "Clear cache for all features", tags = "Features")
  @ApiResponses(value = { //
      @ApiResponse(code = 200, message = "Feature cache cleaned"), //
      @ApiResponse(code = 500, message = "Internal server error") //
  })
  @DeleteMapping(path = "/cache")
  public ResponseEntity<Void> clearCache() {
    service.clearCache();
    return ok();
  }

}
