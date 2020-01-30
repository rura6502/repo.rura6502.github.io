package io.github.rura6502.jpa_rest_query_language_query_dsl_web_support;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
  }

  @Autowired
  private UserRepository userRepository;
  
  @Bean
  CommandLineRunner runner() {
    return args -> {
      User user1 = userRepository.save(new User(0L, "JONGSEONG", "WOO", Gender.FEMALE, City.BUSAN, "rura6502@hotmail.com"));
      User user2 = userRepository.save(new User(0L, "KILDONG", "KIM", Gender.MALE, City.SEOUL, "rura6502@hotmail.com"));


    };
  }



}
