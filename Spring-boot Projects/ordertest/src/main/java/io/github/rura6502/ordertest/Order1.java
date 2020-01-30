package io.github.rura6502.ordertest;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Order0
 */
@Order(1)
@Configuration
public class Order1 {

  public Order1() {
    System.out.println("Hello, I am Order1");
  }
}