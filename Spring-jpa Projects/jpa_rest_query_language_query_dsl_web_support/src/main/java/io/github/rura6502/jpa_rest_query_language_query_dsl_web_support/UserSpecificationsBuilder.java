package io.github.rura6502.jpa_rest_query_language_query_dsl_web_support;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

/**
 * UserSpecificationsBuilder
 */
public class UserSpecificationsBuilder {

  private List<SearchCriteria> params;

  public UserSpecificationsBuilder with(String key, String operation, Object value, String prefix, String suffix) {

    SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
    if (op != null) {
      if (op == SearchOperation.EQUALITY) {
        boolean startWithAsterisk = prefix.contains("*");
        boolean endWithAsterisk = suffix.contains("*");

        if (startWithAsterisk && endWithAsterisk) {
          op = SearchOperation.CONTAINS;
        } else if (startWithAsterisk) {
          op = SearchOperation.ENDS_WITH;
        } else if (endWithAsterisk) {
          op = SearchOperation.STARTS_WITH;
        }
      }
      params.add(new SearchCriteria(key, op, value));
    }
    return this;
  }

  public Specification<User> build() {
    if (params.size() == 0) {
      return null;
    }

    Specification<User> result = new UserSpecification(params.get(0));

    for (int i = 1; i < params.size(); i++) {
      result = params.get(i).isOrPredicate() ? Specification.where(result).or(new UserSpecification(params.get(i)))
          : Specification.where(result).and(new UserSpecification(params.get(i)));
    }

    return result;
  }
}