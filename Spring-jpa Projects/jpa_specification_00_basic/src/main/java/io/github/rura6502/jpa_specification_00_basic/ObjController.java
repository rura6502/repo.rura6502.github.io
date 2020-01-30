package io.github.rura6502.jpa_specification_00_basic;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ObjController
 */
@RestController
public class ObjController {

  @Autowired
  private ObjRepository repo;

  @RequestMapping(method = RequestMethod.GET, value = "/users")
  @ResponseBody
  public List<Obj> search(@RequestParam(value = "search") String search, Pageable pageable) {
    ObjSpecsBuilder builder = new ObjSpecsBuilder();
    Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
    Matcher matcher = pattern.matcher(search + ",");
    while (matcher.find()) {
      builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
    }

    Specification<Obj> spec = builder.build();
    return repo.findAll(spec, pageable).getContent();
  }
}