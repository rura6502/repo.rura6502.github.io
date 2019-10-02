package io.github.rura6502.jpa_change_detection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
  }

  @Autowired
  PService pSrv;

  @Autowired
  PRepository pRepo;

  @Bean
  CommandLineRunner runner() {
    return args -> {
      pRepo.save(new Person(0L, "woo"));
    };
  }

  @RestController
  class TC {
    @GetMapping("/{personId}/{newName}")
    public void test(
      @PathVariable("personId") Long personId
      , @PathVariable("newName") String newName
    ) {
      pSrv.changeNameById(personId, newName);
    }

    @GetMapping("/test")
    public void test1() {
      System.out.println(pRepo.findAll());
    }
  }

}
