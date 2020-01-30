package io.github.rura6502.jpa_one_to_many;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * ManyRepo
 */
public interface ManyRepo extends JpaRepository<Many, Long> {

  
  // @Query("select m from Many m inner join m.oneId o where o.value=:one and m.value=:two")
  // public Many findOneByOneValueAndManyValue(
  //   @Param("one") String one
  //   , @Param("two") String two);
}