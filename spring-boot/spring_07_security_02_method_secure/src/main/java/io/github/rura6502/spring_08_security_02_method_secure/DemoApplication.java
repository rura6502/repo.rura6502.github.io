package io.github.rura6502.spring_08_security_02_method_secure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @RestController
  class TestController {
    @Autowired TestService testService;
    
    @GetMapping("/a")
    @Secured({"ROLE_A"})
    public String a() {return "Hello, A!";}

    @GetMapping("/b")
    @PreAuthorize("hasAuthority('B')")
    public String b() {return "Hello, B!";}

    @GetMapping("/b1")
    public String b_hasRoleA() {return testService.preAuthorizeTest_hasRoleA();}
    @GetMapping("/b2")
    public String b_hasRoleB() {return testService.preAuthorizeTest_hasRoleB();}

    @GetMapping("/c")
    public User c() {return testService.findByUser_user_B_will_be_failed();}

    @GetMapping("/d")
    public List<User> d() {return testService.usingPostFilter();}
  }

  @Service
  class TestService {

    @PreAuthorize("hasRole('A')")
    public String preAuthorizeTest_hasRoleA() {
      return "this is preAuthorizeTest_hasRoleA";
    }
    @PreAuthorize("hasRole('B')")
    public String preAuthorizeTest_hasRoleB() {
      return "this is preAuthorizeTest_hasRoleB";
    }

    @PostAuthorize("returnObject.name == authentication.name")
    public User findByUser_user_B_will_be_failed() {
      return new User("a", "busan");
    }

    @PostFilter("filterObject.name == authentication.name")
    public List<User> usingPostFilter() {
      List<User> datas = new ArrayList<>();
      datas.addAll(Arrays.asList(
        new User("a", "busan1")
        , new User("a", "seoul2")
        , new User("b", "busan2")
        , new User("b", "ulsan1")
      ));
      return datas;
    }
  }

  @Data @AllArgsConstructor
  class User {
    String name, city;
  }

}
