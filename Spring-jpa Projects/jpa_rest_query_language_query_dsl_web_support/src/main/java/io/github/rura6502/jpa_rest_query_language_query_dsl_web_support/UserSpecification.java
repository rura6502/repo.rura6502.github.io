package io.github.rura6502.jpa_rest_query_language_query_dsl_web_support;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.AllArgsConstructor;

/**
 * UserSpecification
 */
@AllArgsConstructor
public class UserSpecification implements Specification<User> {

  private SearchCriteria criteria;

@Override
public Predicate toPredicate(Root<User> root
                                                , CriteriaQuery<?> query
                                                , CriteriaBuilder criteriaBuilder) {
  switch (criteria.getOperation()) {
    case EQUALITY:
        return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
    case NEGATION:
        return criteriaBuilder.notEqual(root.get(criteria.getKey()), criteria.getValue());
    case GREATER_THAN:
        return criteriaBuilder.greaterThan(root.<String> get(
          criteria.getKey()), criteria.getValue().toString());
    case LESS_THAN:
        return criteriaBuilder.lessThan(root.<String> get(
          criteria.getKey()), criteria.getValue().toString());
    case LIKE:
        return criteriaBuilder.like(root.<String> get(
          criteria.getKey()), criteria.getValue().toString());
    case STARTS_WITH:
        return criteriaBuilder.like(root.<String> get(criteria.getKey())
                                          , criteria.getValue() + "%");
    case ENDS_WITH:
        return criteriaBuilder.like(root.<String> get(criteria.getKey())
                                          , "%" + criteria.getValue());
    case CONTAINS:
        return criteriaBuilder.like(root.<String> get(
          criteria.getKey()), "%" + criteria.getValue() + "%");
    default:
        return null;
    }
}

  
}