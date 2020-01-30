package io.github.rura6502.jpa_rest_query_language_query_dsl_web_support;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * UserRepository
 */
public interface UserRepository
                extends JpaRepository<User, Long>
                            , JpaSpecificationExecutor<User> {

  
}