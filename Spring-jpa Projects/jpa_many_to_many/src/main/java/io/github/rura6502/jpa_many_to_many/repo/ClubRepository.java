package io.github.rura6502.jpa_many_to_many.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.rura6502.jpa_many_to_many.domain.Club;

public interface ClubRepository extends JpaRepository<Club, Long> {
  
}