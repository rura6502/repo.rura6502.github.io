package io.github.rura6502.jpa_specification_00_basic;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * ObjSpecification
 */
@RequiredArgsConstructor
public class ObjSpecification implements Specification<Obj> {

  @NonNull
  private ObjCriteria criteria;

	@Override
  public Predicate toPredicate(Root<Obj> root
                                                  , CriteriaQuery<?> query
                                                  , CriteriaBuilder builder) {

    if (criteria.getOperation().equalsIgnoreCase(">")) {
      return builder.greaterThanOrEqualTo(
        root.<String> get(criteria.getKey()), criteria.getValue().toString());
    } 
    else if (criteria.getOperation().equalsIgnoreCase("<")) {
        return builder.lessThanOrEqualTo(
          root.<String> get(criteria.getKey()), criteria.getValue().toString());
    } 
    else if (criteria.getOperation().equalsIgnoreCase(":")) {
        if (root.get(criteria.getKey()).getJavaType() == String.class) {
            return builder.like(
              root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
        } else {
            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
    }
    return null;
	}

  
}