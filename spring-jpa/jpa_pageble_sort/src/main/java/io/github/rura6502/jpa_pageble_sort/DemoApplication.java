package io.github.rura6502.jpa_pageble_sort;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
  }
  
  @Autowired
  PersonRepository pr;

  @Bean
  CommandLineRunner runner() {
    return args -> {
      pr.saveAll(List.of(
        new Person(0L, "woo", 20)
        , new Person(0L, "kim", 23)
        , new Person(0L, "hwang", 13)
        , new Person(0L, "sang", 55)
        , new Person(0L, "lee", 65)
        , new Person(0L, "loo", 25)
        , new Person(0L, "bong", 10)
        , new Person(0L, "wang", 5)
        , new Person(0L, "ho", 30)
        , new Person(0L, "im", 34)
        , new Person(0L, "poo", 54)
        , new Person(0L, "qua", 87)
        , new Person(0L, "oh", 56)
      ));

      System.out.println(
        pr.findAll(PageRequest.of(1, 3, Sort.by("age").descending())).getContent()
      );
    };
  }

}
