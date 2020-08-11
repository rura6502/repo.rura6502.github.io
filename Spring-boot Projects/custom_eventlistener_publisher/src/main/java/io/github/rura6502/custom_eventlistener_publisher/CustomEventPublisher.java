package io.github.rura6502.custom_eventlistener_publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomEventPublisher {

  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  public void doStuffAndPublishAnEvent(final String message) {
      System.out.println("Publishing custom event. ");
      
      applicationEventPublisher.publishEvent(new CustomEvent(this, message));
  }
}