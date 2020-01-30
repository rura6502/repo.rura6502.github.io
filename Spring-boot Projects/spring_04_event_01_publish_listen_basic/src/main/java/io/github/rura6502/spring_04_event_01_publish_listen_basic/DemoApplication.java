package io.github.rura6502.spring_04_event_01_publish_listen_basic;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import lombok.Data;

@SpringBootApplication
public class DemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
  @Autowired
  EventGenerator eventGenerator;
  @Bean
  CommandLineRunner runner() {
    return args -> {
      while (true) {
        eventGenerator.generate(new TestEvent(LocalDateTime.now().toString()));
        Thread.sleep(1000);
      }
    };
  }
}
@Component
class EventGenerator {
  @Autowired
  EventPublisher eventPublisher;
  public void generate(TestEvent testEvent) {
    System.out.println("[GENERATOR] : " + testEvent);
    eventPublisher.publish(testEvent);
  }
}
@Component
class EventPublisher implements ApplicationEventPublisherAware {
  private ApplicationEventPublisher aep;
  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.aep = applicationEventPublisher;
  }
  public void publish(TestEvent testEvent) {
    System.out.println("[PUBLISH] : " + testEvent);
    aep.publishEvent(testEvent);
  }
}
@Data
class TestEvent extends ApplicationEvent {
  String message;
  public TestEvent(String message) {
    super(message);
    this.message = message;
  }
}