package io.github.rura6502.ordertest;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Order0
 */
@Order(-1)
@Configuration
public class Order_1 {

  public Order_1() {
    System.out.println("Hello, I am Order-1");
  }
}