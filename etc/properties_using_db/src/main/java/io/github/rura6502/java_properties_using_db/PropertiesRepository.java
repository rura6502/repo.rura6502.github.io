package io.github.rura6502.java_properties_using_db;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PropertiesRepository
 */
public interface PropertiesRepository extends JpaRepository<Properties, Key> {

  
}