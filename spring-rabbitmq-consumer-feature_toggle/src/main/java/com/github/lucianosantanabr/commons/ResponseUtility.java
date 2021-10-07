package com.github.lucianosantanabr.commons;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ResponseUtility {

  default <T> ResponseEntity<T> response(HttpStatus status, T body) {
    return ResponseEntity.status(status).body(body);
  }

  default <T> ResponseEntity<T> response(HttpStatus status) {
    return response(status, null);
  }

  default <T> ResponseEntity<T> unauthorized(T body) {
    return response(HttpStatus.UNAUTHORIZED, body);
  }

  default <T> ResponseEntity<T> unauthorized() {
    return unauthorized(null);
  }

  default <T> ResponseEntity<T> noContent() {
    return response(HttpStatus.NO_CONTENT);
  }

  default <T> ResponseEntity<T> conflict(T body) {
    return response(HttpStatus.CONFLICT, body);
  }

  default <T> ResponseEntity<T> conflict() {
    return conflict(null);
  }

  default <T> ResponseEntity<T> created(T body) {
    return response(HttpStatus.CREATED, body);
  }

  default <T> ResponseEntity<T> created() {
    return created(null);
  }

  default <T> ResponseEntity<T> failedDependency(T body) {
    return response(HttpStatus.FAILED_DEPENDENCY, body);
  }

  default <T> ResponseEntity<T> failedDependency() {
    return failedDependency(null);
  }

  default <T> ResponseEntity<T> preconditionFailed(T body) {
    return response(HttpStatus.PRECONDITION_FAILED, body);
  }

  default <T> ResponseEntity<T> preconditionFailed() {
    return preconditionFailed(null);
  }

  default <T> ResponseEntity<T> notAcceptable(T body) {
    return response(HttpStatus.NOT_ACCEPTABLE, body);
  }

  default <T> ResponseEntity<T> notAcceptable() {
    return notAcceptable(null);
  }

  default <T> ResponseEntity<T> accepted(T body) {
    return response(HttpStatus.ACCEPTED, body);
  }

  default <T> ResponseEntity<T> accepted() {
    return accepted(null);
  }

  default <T> ResponseEntity<T> ok(T body) {
    return response(HttpStatus.OK, body);
  }

  default <T> ResponseEntity<T> ok() {
    return ok(null);
  }

  default <T> ResponseEntity<T> internalServerError(T body) {
    return response(HttpStatus.INTERNAL_SERVER_ERROR, body);
  }

  default <T> ResponseEntity<T> internalServerError() {
    return internalServerError(null);
  }
}
