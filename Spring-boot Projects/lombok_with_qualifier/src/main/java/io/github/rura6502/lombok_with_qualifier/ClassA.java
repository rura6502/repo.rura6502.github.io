package io.github.rura6502.lombok_with_qualifier;

import org.springframework.stereotype.Component;

/**
 * ClassA
 */
@Component
public class ClassA implements InterfaceA {

  @Override
  public String getValue() {
    return "I am ClassA";
  }

  
}