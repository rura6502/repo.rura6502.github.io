package io.github.rura6502.spring_03_aop_01_basic;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Autowired
  Test test;
  @Bean
  CommandLineRunner runner() {
    return args -> {
      try {
        test.act1("aaa", "bbb");
        System.out.println("\n");
        test.act1("ccc", "ddd");
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    };
  }

}


@Component
class Test {
  public String act1(String val1, String val2) throws Exception {
    System.out.println(val1 + ", " + val2);
    if (val2.equals("ddd"))
      throw new Exception("this is Exception test");
    return "Hello, " + val1 + ", " + val2;
  }
}

@Aspect
@Component
class TestAspect {

  /**
   * pointcut : * Test.act1(..),
   *  - * means all modifier. public/proected/private
   *  - .. means doesn't consider mount of arguments.
   * advice : @Before/After/AfterReturning/AfterThrowing/Around
   */

  @Before("execution(* Test.act1(..))") 
  public void before(JoinPoint joinPoint) {   // before is 'advice method'
    System.out.println("#### I am before : " + Arrays.asList(joinPoint.getArgs()));
  }
  @After("execution(* Test.act1(..))")
  public void after(JoinPoint joinPoint) {
    System.out.println("#### I am After");
  }
  @AfterReturning(pointcut = "execution(* Test.act1(..))", returning = "result")
  public void afterReturning(JoinPoint joinPoint, Object result) {
    System.out.println("#### I am AfterReturning : " + result);
  }

  @AfterThrowing(pointcut = "execution(* Test.act1(..))", throwing = "exception")
  public void afterThrowing(JoinPoint joinPoint, Exception exception) {
    System.out.println("#### I am AfterThrowing : " + exception.getMessage());
  }
}