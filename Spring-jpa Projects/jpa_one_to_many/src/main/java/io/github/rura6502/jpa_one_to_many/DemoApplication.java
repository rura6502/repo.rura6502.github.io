package io.github.rura6502.jpa_one_to_many;

import java.util.List;

import javax.transaction.Transactional;

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
  private ManyRepo mr;
  @Autowired
  private OneRepo or;
  
  @Bean
  @Transactional
  CommandLineRunner runner() {
    return args -> {
      One one1 = or.save(new One(0L, "1"));
      One one2 = or.save(new One(0L, "2"));
      mr.save(new Many(0L, "a", "1-a", one1.getId()));
      mr.save(new Many(0L, "b", "1-b", one1.getId()));
      mr.save(new Many(0L, "c", "1-c", one1.getId()));
      mr.save(new Many(0L, "d", "2-d", one2.getId()));
      mr.save(new Many(0L, "e", "2-2", one2.getId()));
    };
  }


  @RestController
  class TestController {

    @GetMapping("/remove_one/{id}")
    public void removeOne(@PathVariable("id") long id) {
      or.deleteById(id);
    }

    @GetMapping("/remove_two/{id}")
    public void removeTwo(@PathVariable("id") long id) {
      mr.deleteById(id);
    }

    @GetMapping
    public List<One> getAll() {
      return or.findAll();
    }

    @GetMapping("/find/{a}/{b}")
    public String find(
    @PathVariable("a") String one
    , @PathVariable("b") String two) {
      System.out.println(or.findOneByOneValueAndManyValue(one, two).getDesc());
      return or.findOneByOneValueAndManyValue(one, two).getDesc();
    }

    // @GetMapping("/del/{a}/{b}")
    // public void del(
    // @PathVariable("a") String one
    // , @PathVariable("b") String two) {
    //   or.deleteOneByOneValueAndManyValue(one, two);
    //   // return or.findOneByOneValueAndManyValue(one, two).getDesc();
    // }
      
  }
  
 
}


