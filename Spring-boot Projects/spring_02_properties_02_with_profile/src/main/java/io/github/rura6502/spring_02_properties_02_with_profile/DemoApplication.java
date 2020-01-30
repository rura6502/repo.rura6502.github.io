package io.github.rura6502.spring_02_properties_02_with_profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Autowired
  Test test;

  @Value("${key1}")
  String key1;

  @Autowired
  TestProperties testProperties;

  @Bean
  CommandLineRunner runner () {
    return args -> {
      // read properties
      System.out.println("\n");
      System.out.println("### test read application.properties");
      System.out.println(key1);

      System.out.println("\n");

      // general POJO
      System.out.println("### test POJO");
      System.out.println(test.key);

      System.out.println("\n");

      // general POJO
      System.out.println("### test @PropertySource");
      System.out.println(testProperties.getKey());
    };
  }
}

@AllArgsConstructor
class Test {
  String key;
}

@Component
@Profile("prod")
class Sub1 extends Test {
  public Sub1() {super("this is prod");}
}
@Component
@Profile("dev")
class Sub2 extends Test {
  public Sub2() {super("this is dev");}
}

@Data
@Configuration
@PropertySource("classpath:test-${spring.profiles.active}.properties")
class TestProperties {
  @Value("${key}")
  String key;
}
