package io.github.rura6502.jpa_01_repository_01_update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Autowired
  private PersonRepository personRepository;

  @Bean
  CommandLineRunner runner() {
    return (args) -> {
      Person a = new Person(0, "woo", "ulsan");
      Person b = new Person(0, "kim", "seoul");
      Person c = new Person(0, "lee", "busan");
      personRepository.save(a);
      personRepository.save(b);
      personRepository.save(c);
      System.out.println(personRepository.findAll());
    };
  }


  @RestController
  class TestController {

    @GetMapping("/test1/{id}")
    public void test1(@PathVariable("id") long id) {
      Person person = new Person();
      person.setId(id);
      person.setName("MMM");
      person.setAddress("JAPAN");
      personRepository.save(person);
    }

    @GetMapping("/test2/{id}")
    public void test2(@PathVariable("id") long id) {
      Person person = personRepository.findById(id).get();
      person.setName("MMM");
      person.setAddress("JAPAN");
      personRepository.save(person);
    }

    @GetMapping("/test3/{id}")
    public void test3(@PathVariable("id") long id) {
      System.out.println(personRepository.findById(id));
    }

  }

}
