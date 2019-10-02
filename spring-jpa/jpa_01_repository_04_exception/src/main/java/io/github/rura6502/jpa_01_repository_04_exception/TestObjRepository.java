package io.github.rura6502.jpa_01_repository_04_exception;

import java.util.Optional;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TestObjRepository
 */
public interface TestObjRepository extends JpaRepository<TestObj, Long> {

  TestObj save(TestObj testObj) throws JdbcSQLIntegrityConstraintViolationException;

  Optional<TestObj> findByName(String name) throws JdbcSQLIntegrityConstraintViolationException;

  
}