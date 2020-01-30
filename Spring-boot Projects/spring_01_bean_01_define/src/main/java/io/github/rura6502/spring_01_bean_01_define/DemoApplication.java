package io.github.rura6502.spring_01_bean_01_define;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DemoApplication {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(TestClassBeanConfig.class);
    System.out.println("######## Test Get Bean from Conetxt ###");

    System.out.println();
    System.out.println();

    TestClass testClass1 = (TestClass) context.getBean("testClass");
    System.out.println("#### test-1 : get bean with name");
    System.out.println(testClass1);

    System.out.println();
    System.out.println();

    TestClass testClass2 = context.getBean("testClass", TestClass.class);
    System.out.println("#### test-2 : get bean with name and class");
    System.out.println(testClass2);

    System.out.println();
    System.out.println();

    TestClass testClass3 = context.getBean(TestClass.class);
    System.out.println("#### test-3 : get bean with class");
    System.out.println(testClass3);

    System.out.println();
    System.out.println();

    TestClass testClass4 = context.getBean("beanA", TestClass.class);
    System.out.println("#### test-4 : get bean with bean name");
    System.out.println(testClass4);
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