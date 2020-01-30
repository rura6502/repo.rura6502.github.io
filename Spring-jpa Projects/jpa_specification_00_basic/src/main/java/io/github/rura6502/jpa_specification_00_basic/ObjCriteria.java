package io.github.rura6502.jpa_specification_00_basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * ObjCriteria
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ObjCriteria {

  @NonNull
  private String key;
  @NonNull
  private String operation;
  @NonNull
  private Object value;

  private boolean orPredicate;
}