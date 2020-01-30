package io.github.rura6502.jpa_01_repository_02_list_contains;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PersonRepository
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

  // List<Person> findAllByPhoneIdsIn(Long id);
}