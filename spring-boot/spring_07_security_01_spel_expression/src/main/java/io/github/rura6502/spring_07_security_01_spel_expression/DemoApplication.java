package io.github.rura6502.spring_07_security_01_spel_expression;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Controller
  class TestContoller {
    @GetMapping("/")
    public String index() {return "index";}
    @GetMapping("/a")
    public String a() {return "a";}
    @GetMapping("/b")
    public String b() {return "b";}
    @GetMapping("/c")
    public String c() {return "c";}
  }

}
