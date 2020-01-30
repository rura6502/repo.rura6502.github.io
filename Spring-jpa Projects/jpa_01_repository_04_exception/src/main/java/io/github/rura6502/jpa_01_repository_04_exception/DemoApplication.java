package io.github.rura6502.jpa_01_repository_04_exception;

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
  TestObjDao toDao;

  @Bean
  CommandLineRunner runner() {
    return args -> {
      TestObj to1 = toDao.save(new TestObj(0L, "우종성"));
      TestObj to2 = toDao.save(new TestObj(0L, "우종성"));
      
      System.out.println(toDao.findById(1L));
      
    };
  }

}
