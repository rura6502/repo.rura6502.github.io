package io.github.rura6502.jpa_02_collection_01_element_collection_basic;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * PersonRepository
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

  @Query("select p from Person p JOIN p.phones ph WHERE ph.company=:company")
  public Collection<Person> findAllByPhoneCompany(@Param("company") String company);
}