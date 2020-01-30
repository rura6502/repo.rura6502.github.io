package io.github.rura6502.jpa_01_repository_03_in;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PersonRepository
 */
public interface PersonBRepository extends JpaRepository<PersonB, Long> {

  Collection<PersonB> findAllByCities(String cities);

  Collection<PersonB> findAllByCitiesIn(List<String> cities);
}