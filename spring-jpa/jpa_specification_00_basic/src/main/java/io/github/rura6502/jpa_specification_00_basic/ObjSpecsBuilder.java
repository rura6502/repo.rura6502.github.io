package io.github.rura6502.jpa_specification_00_basic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

/**
 * ObjSpecsBuilder
 */
public class ObjSpecsBuilder {

  private final List<ObjCriteria> params;

  public ObjSpecsBuilder() {
        params = new ArrayList<ObjCriteria>();
    }

  public ObjSpecsBuilder with(String key, String operation, Object value) {
    params.add(new ObjCriteria(key, operation, value));
    return this;
  }

  public Specification<Obj> build() {
    if (params.size() == 0) {
      return null;
    }

    List<Specification<Obj>> specs = params.stream().map(ObjSpecification::new).collect(Collectors.toList());

    Specification result = specs.get(0);

    for (int i = 1; i < params.size(); i++) {
      result = params.get(i).isOrPredicate() ? Specification.where(result).or(specs.get(i))
          : Specification.where(result).and(specs.get(i));
    }
    return result;
  }
}