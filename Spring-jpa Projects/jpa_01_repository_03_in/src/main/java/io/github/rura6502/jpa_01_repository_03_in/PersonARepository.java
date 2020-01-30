package io.github.rura6502.jpa_01_repository_03_in;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PersonRepository
 */
public interface PersonARepository extends JpaRepository<PersonA, Long> {

  Collection<PersonA> findAllByCityIn(String[] cities);
}