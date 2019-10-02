package io.github.rura6502.jpa_02_collection_01_element_collection_basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
  }

  @Autowired
  PersonRepository personRepository;
  
  @Bean
  CommandLineRunner runner() {
    return args -> {
      Phone phone1 = new Phone("LGT", "00-01");
      Phone phone2 = new Phone("KT", "00-02");
      Person person1 = new Person(0L, "woo", List.of(phone1, phone2));

      Phone phone3 = new Phone("LGT", "01-01");
      Phone phone4 = new Phone("SKT", "01-02");
      Person person2 = new Person(0L, "kim", List.of(phone3, phone4));
      
      personRepository.save(person1);
      personRepository.save(person2);

      System.out.println(personRepository.findAll());

      System.out.println("### phone company SKT");
      System.out.println(personRepository.findAllByPhoneCompany("SKT"));


    };
  }

}
