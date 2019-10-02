package io.github.rura6502.spring_08_mongodb_01_basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
  }
  
  @Document(collection="user")
  class User {

    @Id
    long id;
    String name;
    int age;
    City city;
  }
  
  enum City {
    SEOUL, BUSAN, ULSAN, DAEGU;
  }

}


