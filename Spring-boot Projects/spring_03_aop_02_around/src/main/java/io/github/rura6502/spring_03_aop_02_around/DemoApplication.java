package io.github.rura6502.spring_03_aop_02_around;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.ToString;

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
      test.act(1, 2);
    };
  }

}

@ToString
@Component
class Test {
  public int act(int a, int b) {
    System.out.println(a + ", " + b + " are in Test");
    return a + b;
  }
}

@Aspect
@Component
@Order(1)
class TestAOP1 {

  @Pointcut("execution(* Test.act(..))")
  public void pointcut(){}

  @Around("pointcut()")
  public Object around(ProceedingJoinPoint pjp) throws Throwable {
    System.out.println("--- I am Order(1)");

    System.out.println("### around start");
    System.out.println("getArgs() : " + Arrays.asList(pjp.getArgs()));
    System.out.println("getKind() : " + pjp.getKind());
    System.out.println("getSignature().getName() : " + pjp.getSignature().getName());
    System.out.println("getSignature().getDeclaringTypeName() : " + pjp.getSignature().getDeclaringTypeName());
    System.out.println("getTarget().getClass().getName() : " + pjp.getTarget().getClass().getName());
    System.out.println("getThis().getClass().getName() : " + pjp.getThis().getClass().getName());

    Object result = pjp.proceed();
    System.out.println("proceed() : " + result);
    return result;
  }
}

@Aspect
@Component
@Order(0)
class TestAOP2 {
  @Around("TestAOP1.pointcut()")
  public Object around(ProceedingJoinPoint pjp) throws Throwable {
    System.out.println("--- I am Order(0)");
    return pjp.proceed();
  }
}