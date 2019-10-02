package io.github.rura6502.authorization_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.rura6502.authorization_server.user.Grant;
import io.github.rura6502.authorization_server.user.User;
import io.github.rura6502.authorization_server.user.UserRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
  }

  @Autowired
  UserRepository ur;
  
  @Bean
  CommandLineRunner runner() {
    return args -> {
      ur.save(new User(0L, "admin", "admin1234", "woo", Grant.ADMIN));
      ur.save(new User(0L, "user", "user1234", "woo", Grant.USER));
    };
  }

}
