package io.github.rura6502.jpa_casecade_basic;

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

  @Autowired Test1Repository t1Repo;
  @Autowired Test2Repository t2Repo;
  
  @Bean
  CommandLineRunner runner() {
    return args -> {

      Test1 t1_1 = t1Repo.save(new Test1(0L, "a", "b", new Test2(0L, "c", "d")));
      
      
    };
  }

}
