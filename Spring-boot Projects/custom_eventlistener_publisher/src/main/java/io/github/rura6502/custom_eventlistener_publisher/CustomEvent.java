package io.github.rura6502.custom_eventlistener_publisher;

import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {
  /**
	 *
	 */
	private static final long serialVersionUID = 1L;
  
  private String message;

  public CustomEvent(Object source, String message) {
    super(source);
    this.message = message;
  }
  public String getMessage() {
    return message;
  }
}