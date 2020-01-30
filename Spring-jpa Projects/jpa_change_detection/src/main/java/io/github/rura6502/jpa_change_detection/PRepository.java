package io.github.rura6502.jpa_change_detection;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PRepository
 */
interface PRepository extends JpaRepository<Person, Long> {

}