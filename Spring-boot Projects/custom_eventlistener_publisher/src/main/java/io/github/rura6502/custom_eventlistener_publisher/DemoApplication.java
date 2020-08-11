package io.github.rura6502.custom_eventlistener_publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Autowired
  private CustomEventPublisher publisher;

  @Bean
  ApplicationRunner runner() {
    return args -> {
      publisher.doStuffAndPublishAnEvent("Call CustomEvent!");
    };
  }


  @EventListener(value = CustomEvent.class)
  public void test(CustomEvent customEvent) {
    System.out.println("hi");
    System.out.println(customEvent.getMessage());
  }

}
