package io.github.rura6502.jpa_01_repository_02_list_contains;

import java.util.Collection;

import org.assertj.core.util.Lists;
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
  PersonRepository personRepository;

  @Autowired
  PhoneRepository phoneRepository;

  @Bean
  CommandLineRunner runner() {
    return args -> {

      Phone phone1 = phoneRepository.save(new Phone(0L, "123-0001"));
      Phone phone2 = phoneRepository.save(new Phone(0L, "123-0002"));
      Phone phone3 = phoneRepository.save(new Phone(0L, "123-0003"));
      Phone phone4 = phoneRepository.save(new Phone(0L, "123-0004"));

      System.out.println(phone1);
      System.out.println(phone2);
      System.out.println(phone3);
      System.out.println(phone4);


      personRepository.save(new Person(0L, "WOO", Lists.list(phone1)));
      personRepository.save(new Person(0L, "KIM", Lists.list(phone2)));
      personRepository.save(new Person(0L, "LEE", Lists.list(phone1, phone2, phone3)));
      personRepository.save(new Person(0L, "HUN", Lists.list(phone3, phone4, phone1)));

      // System.out.println(pRepository.findAllByPhoneIdsIn(1L));

    };

    
  }

}
