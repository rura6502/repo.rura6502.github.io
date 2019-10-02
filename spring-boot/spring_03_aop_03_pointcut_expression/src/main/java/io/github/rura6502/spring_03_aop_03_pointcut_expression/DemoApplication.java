package io.github.rura6502.spring_03_aop_03_pointcut_expression;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Autowired
  TestClass testClass;

  @Bean
  CommandLineRunner runner() {
    return args -> {
      testClass.test1();
      testClass.test2();
      testClass.test3(1, "b");
      testClass.test4();
    };
  }
}

@Component
class TestClass {
  public void test1() {System.out.println("test1");}
  protected void test2() {System.out.println("test2");}
  public String test3(int a, String b) {System.out.println("test3");return a + b;}
  @CustomAOP_Annotation
  public void test4() {System.out.println("test4");}
}

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME) @Documented
@interface CustomAOP_Annotation {}


@Aspect
@Configuration
class AspectTest {
  @Before("execution(protected * TestClass.*(..))")
  public void modifier_test() {
    System.out.println("[AOP] : modifier test");
  }

  @Before("execution(public String TestClass.*(..))")
  public void return_type_test() {
    System.out.println("[AOP] : return type test");
  }

  @Before("execution(* TestClass.*(int, ..))")
  public void parameter_test() {
    System.out.println("[AOP] : parameter test");
  }

  @Before("@annotation(CustomAOP_Annotation)")
  // == "within(CustomAOP_Annotation)"
  public void annotation_test() {
    System.out.println("[AOP] : annotation test");
  }

  // within(io.github.rura6502.spring_03_aop_03_pointcut_expression..*) : 하위 패키지 모두 적용
  // within(TestInterface+) : 'TestInterfcae'를 구현한 모든 클래스
  @Before("within(io.github.rura6502.spring_03_aop_03_pointcut_expression.*)")
  public void wildcard_test() {
    System.out.println("[AOP] : wildcard test");
  }
}