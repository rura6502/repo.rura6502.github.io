package io.github.rura6502.jpa_rest_query_language_query_dsl_web_support;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Joiner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;

/**
 * UserController
 */
@RestController
public class UserController {
  
  @NonNull
  private UserRepository userRepository;

  @RequestMapping(method = RequestMethod.GET, value = "/users")
  public List<User> findAllBySpecification(@RequestParam(value = "search") String search) {
    UserSpecificationsBuilder builder = new UserSpecificationsBuilder();
    String operationSetExper = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
    Pattern pattern = Pattern.compile(
      "(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
    Matcher matcher = pattern.matcher(search + ",");
    while (matcher.find()) {
        builder.with(
          matcher.group(1), 
          matcher.group(2), 
          matcher.group(4), 
          matcher.group(3), 
          matcher.group(5));
    }
 
    Specification<User> spec = builder.build();
    return userRepository.findAll(spec);
}
}