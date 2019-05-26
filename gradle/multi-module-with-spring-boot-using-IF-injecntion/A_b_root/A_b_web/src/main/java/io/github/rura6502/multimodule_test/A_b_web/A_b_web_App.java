package io.github.rura6502.multimodule_test.A_b_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.rura6502.multimodule_test.A_b_comm.A_b_comm_Class;
import io.github.rura6502.multimodule_test.A_web_comm.A_web_comm_Class;

/**
 * A_a_web_App
 */
@SpringBootApplication
public class A_b_web_App {

  A_web_comm_Class a_web_comm_Class = new A_web_comm_Class();
  A_b_comm_Class a_b_comm_Class = new A_b_comm_Class();

  public static void main(String[] args) {
    SpringApplication.run(A_b_web_App.class, args);
  }
}