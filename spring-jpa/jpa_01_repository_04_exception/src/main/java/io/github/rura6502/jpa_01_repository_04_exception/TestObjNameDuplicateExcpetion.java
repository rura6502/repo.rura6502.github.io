package io.github.rura6502.jpa_01_repository_04_exception;

/**
 * TestObjException
 */
public class TestObjNameDuplicateExcpetion extends RuntimeException {

  TestObjNameDuplicateExcpetion(String message) {
    super(message);
  }
}