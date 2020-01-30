package io.github.rura6502.ordertest;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Order0
 */
@Order(0)
@Configuration
public class Order0 {

  public Order0() {
    System.out.println("Hello, I am Order0");
  }
}