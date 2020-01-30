package io.github.rura6502.lombok_with_qualifier;

import org.springframework.stereotype.Component;

/**
 * ClassB
 */
@Component
public class ClassB implements InterfaceA {

  @Override
  public String getValue() {
    return "I am ClassB";
  }

  
}