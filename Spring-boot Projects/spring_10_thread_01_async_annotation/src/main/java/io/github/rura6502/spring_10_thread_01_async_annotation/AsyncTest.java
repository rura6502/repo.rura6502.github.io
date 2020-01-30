package io.github.rura6502.spring_10_thread_01_async_annotation;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * AsyncTest
 */
@Component
@Slf4j
public class AsyncTest {

  @PostConstruct
  public void init() {
    log.info("init()");
    this.asyncTest();
  }

  @Async
  private void asyncTest() {
    log.info("Hello, I am asyncTest()");
  }
}