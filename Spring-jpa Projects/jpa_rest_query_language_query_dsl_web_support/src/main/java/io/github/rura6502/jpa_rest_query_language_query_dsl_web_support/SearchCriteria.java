package io.github.rura6502.jpa_rest_query_language_query_dsl_web_support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * SearchCriteria
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SearchCriteria {

  @NonNull
  private String key;
  @NonNull
  private SearchOperation operation;
  @NonNull
  private Object value;
  private boolean orPredicate;
  

  
}