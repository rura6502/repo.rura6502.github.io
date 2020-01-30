package io.github.rura6502.resource_server;

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
  private BookRepository br;

  @Bean
  CommandLineRunner runner() {
    return args -> {
      br.save(new Book(0L, "JAVA_BASIC", "woo", 30_000));
      br.save(new Book(0L, "DATA_SCIENCE", "LEE", 38_000));
      br.save(new Book(0L, "VUEJS START! DOIT!", "KIMGUN", 23_000));
    };
  }

}
