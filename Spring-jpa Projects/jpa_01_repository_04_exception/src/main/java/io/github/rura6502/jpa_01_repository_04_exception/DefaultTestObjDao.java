package io.github.rura6502.jpa_01_repository_04_exception;

import java.util.Optional;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.stereotype.Repository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * DefaultTestObjDao
 */
@Repository
@RequiredArgsConstructor
public class DefaultTestObjDao implements TestObjDao {

  @NonNull
  private final TestObjRepository toRepository;

  @Override
  public TestObj save(TestObj testObj) {
    try {
      testObj = toRepository.save(testObj);
    } catch (JdbcSQLIntegrityConstraintViolationException e) {

    }
    return testObj;
  }

  @Override
  public Optional<TestObj> findById(Long testObjId) {
    return toRepository.findById(testObjId);
  }

  @Override
  public TestObj findByName(String name) {
    TestObj testObj = null;
    try {
      testObj = toRepository.findByName(name).get();
    } catch (JdbcSQLIntegrityConstraintViolationException e) {

    }
    return testObj;
  }

  
}