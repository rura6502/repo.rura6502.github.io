package io.github.rura6502.spring_01_bean_05_post_processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import lombok.ToString;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  CommandLineRunner runner() {
    return args -> {

    };
  }

  @Bean (initMethod = "init")
  public Test test() {
    return new Test("Hello, Test!");
  }

}

@ToString
class Test {
  String key;
  public Test(String key) {
    this.key = key;
  }
  public void init() {
    System.out.println("--- init in Test");
    this.key += " Good Morning!";
  }
}

@Component
class PostProcessorTest implements BeanPostProcessor {

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if (bean instanceof Test) {
      System.out.println("postProcessAfterInitialization - bean name '" + beanName + "' : " + bean);
    }
    return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    if (bean instanceof Test) {
      System.out.println("postProcessBeforeInitialization- bean name '" + beanName + "' : " + bean);
    }
    return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
  }
  
}