package io.github.rura6502.jpa_many_to_many.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.rura6502.jpa_many_to_many.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
  
}