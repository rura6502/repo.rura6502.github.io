package io.github.rura6502.asciidoctor_example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @GetMapping("/test1/{test1}")
  public String test1(@PathVariable("test1") String test1) {
    return "test1 is : " + test1;
  }

  @PostMapping("/test2")
  public String test2(@RequestBody String test2) {
    return "test2 is : " + test2;
  }
}