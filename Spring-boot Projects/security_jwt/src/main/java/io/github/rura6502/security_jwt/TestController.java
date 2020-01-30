package io.github.rura6502.security_jwt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 */
@RestController
public class TestController {

  @GetMapping
  public String index() {
    return "Hello World!";
  }

  
}