package io.github.rura6502.spring_01_bean_02_autowired;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }


  // for test 1
  @Autowired
  TestClass testClass1;


  // for test 2
  @Autowired
  @Qualifier("beanA")
  TestClass testClass2;


  // for test 3
  TestClass testClass3;
  @Bean
  public TestClass test(
      @Qualifier("beanA") TestClass testClass) {
    this.testClass3 = testClass;
    return testClass;
  }

  
  // for test 4
  @Resource(name = "beanA")  // java standard. @Autowired + @Qualifier
  TestClass testClass4;
  
  @Bean
  CommandLineRunner runner() {
    return args -> {

      System.out.println("\n\n");

      System.out.println("### test1 : using @Autowired");
      System.out.println(testClass1);

      System.out.println("\n\n");

      System.out.println("### test2 : using @Qualifier");
      System.out.println(testClass2);

      System.out.println("\n\n");

      System.out.println("### test3 : using @Qualifier in parameter");
      System.out.println(testClass3);
      
      System.out.println("\n\n");

      System.out.println("### test4 : using @Resource in parameter");
      System.out.println(testClass4);
    };
  }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class TestClass {
  private String keyA;
  private String keyB;
}

@Component
class TestClassBeanConfig {

  @Bean
  @Primary
  public TestClass testClass() {
    TestClass testClass = new TestClass();
    testClass.setKeyA("A");
    testClass.setKeyB("B");
    return testClass;
  }

  @Bean("beanA")
  public TestClass setBeanA() {
    TestClass testClass = new TestClass();
    testClass.setKeyA("A in BeanA");
    testClass.setKeyB("B in BeanB");
    return testClass;
  }
}