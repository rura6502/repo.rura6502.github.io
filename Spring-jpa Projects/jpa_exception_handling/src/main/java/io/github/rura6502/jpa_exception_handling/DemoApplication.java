package io.github.rura6502.jpa_exception_handling;

import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.h2.jdbc.JdbcSQLDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.AllArgsConstructor;
import lombok.Data;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
  }
  
  @Autowired
  UserRepository userRepo;

  @Autowired
  ObjectMapper objectMapper;

  @RestController
  class TestController {
    @GetMapping("/test")
    public void test() {
      userRepo.save(new User(null, "abcd", 101, "123.", "123"));
      userRepo.save(new User(null, "abcd", 101, "123.", "123"));
    }
  }

  @ControllerAdvice
  class TestAdvisor {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<Object> handleException(DataIntegrityViolationException e, WebRequest request) {
      SQLException sqlException = objectMapper.convertValue(e.getRootCause(), SQLException.class);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save User. cause : " + sqlException.getMessage());
    }
  }

  // @Bean
  // ApplicationRunner runner() {
  //   return args -> {
  //     try {
  //     userRepo.save(new User(null, "abcd", 101, "this is memo1.", "this is memo2."));
  //     } catch (DataIntegrityViolationException ex) {
  //       ex.printStackTrace();
        
  //     }
  //   };
  // }

}


interface UserRepository extends JpaRepository<User, Long> {}

@Entity
@Data
@AllArgsConstructor
class User{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String name;

  @Column(scale = 100)
  private int age;

  @NotNull
  @Column(length = 10)
  private String memo1;

  @NotNull
  @Column(length = 10)
  private String memo2;
}