package io.github.rura6502.controller_test_basic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * BookRepository
 */
public interface BookRepository extends JpaRepository<Book, Long> {

  
}