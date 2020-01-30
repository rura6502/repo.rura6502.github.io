package io.github.rura6502.spring_01_bean_04_init_destroy;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  CommandLineRunner runner() {
    return args -> {
      System.out.println("### start sleep 2sec");
      Thread.sleep(2000);
      test1(); // test1 instance was created when IOC start
      test2(); // this point is test2 Lazy creation point 

    };
  }

  @Bean(initMethod = "init", destroyMethod = "destroy")
  public Test1 test1() {
    return new Test1();
  }

  @Bean
  @Lazy
  public Test2 test2() {
    return new Test2();
  }

}

class Test1 {
  public void init() {
    System.out.println("---- init in Test1");
  }
  public void destroy() throws Exception {
    System.out.println("---- destroy in Test1");
  }
}

class Test2 {
  @PostConstruct
  public void init() {
    System.out.println("----- init in Test2");
  }
  @PreDestroy
  public void destroy() {
    System.out.println("----- destroy in Test2");
  }
}