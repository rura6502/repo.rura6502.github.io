package io.github.rura6502.jpa_specification_00_basic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * ObjRepository
 */
public interface ObjRepository
                              extends JpaRepository<Obj, Long>
                                            , JpaSpecificationExecutor<Obj> {
  
  Page<Obj> findAll(Specification<Obj> specification, Pageable pageable);


  
}