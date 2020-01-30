package io.github.rura6502.jpa_01_repository_04_exception;

/**
 * CustomException
 */
public class CustomException extends RuntimeException {

  public CustomException(String message) {
    super(message);
  }

  private CustomException() {

  }
}