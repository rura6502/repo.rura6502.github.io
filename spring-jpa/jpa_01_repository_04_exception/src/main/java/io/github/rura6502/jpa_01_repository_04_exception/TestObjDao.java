package io.github.rura6502.jpa_01_repository_04_exception;

import java.util.Optional;

/**
 * TestObjDao
 */
public interface TestObjDao {

  TestObj save(TestObj testObj);

  Optional<TestObj> findById(Long testObjId);

  TestObj findByName(String name);
}