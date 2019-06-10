package io.github.rura6502.spring_01_bean_03_scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
/**
 * ## bean scope
 * singleton : DEFALT, only one bean instance in IoC Container
 * prototype : each request has new bean instance
 * request : (ServletContext Only) each HTTP request has new bean instance
 * globalSession : (ServletContext Only) each Global HTTP Session has new bean instance
 * session : (ServletContext Only) 1 session, 1 bean instance
 * application : (ServletContext Only) 1 bean instance in life cycle of a servlet context. multiple servlet-based applications running in the same ServletContext has only one instance. but singleton each ServletContext have each instance
 * websocket : (ServletContext Only) web socket session
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Autowired Bean_Singleton bean_Singleton1;
  @Autowired Bean_Singleton bean_Singleton2;
  @Autowired Bean_Singleton bean_Singleton3;

  @Autowired Bean_Prototype bean_Prototype1;
  @Autowired Bean_Prototype bean_Prototype2;
  @Autowired Bean_Prototype bean_Prototype3;

  @Autowired Bean_Request bean_Request;

  @Bean
  CommandLineRunner runner() {
    return args -> {

      System.out.println("\n");

      System.out.println("test bean scope 'singleton' : every instances are same");
      System.out.println(bean_Singleton1);
      System.out.println(bean_Singleton2);
      System.out.println(bean_Singleton3);

      System.out.println("\n");

      System.out.println("test bean scope 'prototype' : every instances are different");
      System.out.println(bean_Prototype1);
      System.out.println(bean_Prototype2);
      System.out.println(bean_Prototype3);

      System.out.println("\n");

      System.out.println("test bean scope 'request' : every request has different instance");
      new RestTemplate().getForEntity("http://localhost:8080/request", Void.class);
      new RestTemplate().getForEntity("http://localhost:8080/request", Void.class);
      new RestTemplate().getForEntity("http://localhost:8080/request", Void.class);
    };
  }

  @RestController
  class TestClass {
    @GetMapping(value="/{beanType}")
    public void getMethodName(@PathVariable("beanType") String beanType) {
      if (beanType.equals("request"))
        System.out.println(bean_Request);
    }
  }
  
  

}

@Component @Scope("singleton")
class Bean_Singleton {}

@Component @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class Bean_Prototype {}

@Component @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
class Bean_Request {}

// @Component @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
// class Bean_Session {}

// @Component @Scope("globalSession")
// class Bean_GlobalSession {}