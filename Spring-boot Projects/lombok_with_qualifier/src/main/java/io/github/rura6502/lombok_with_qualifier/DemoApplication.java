package io.github.rura6502.lombok_with_qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
  }

  @Autowired
  Test test;
  
  @Bean
  CommandLineRunner runner() {
    return args -> {
      test.test();
    };
  }

}

@Component
@RequiredArgsConstructor
// (
//   onConstructor = @__({@Autowired}), copyInstanceVarAnnotations = true)
// )
class Test {

  @Qualifier("classA")
  private final InterfaceA cA;

  @Qualifier("classB")
  private final InterfaceA cB;

  void test() {
    System.out.println(cA.getValue());
    System.out.println(cB.getValue());
  }
}
