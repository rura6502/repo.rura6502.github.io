package io.iochord.dev.abcd.event_source_basic_sample;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  public SseEmitter sseEmitter() {
    return new SseEmitter();
  }

  @Autowired
  SseEmitter sseEmitter;

  @Bean
  CommandLineRunner runner() {
    return args -> {

      new Thread(() -> {

        while (true) {
          try {
            sseEmitter.send(LocalDateTime.now().toString());
            Thread.sleep(2000);
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          } catch (InterruptedException ie) {
            ie.printStackTrace();
          }
        }
      }).run();
    };
  }


  @RestController
  class TestController {

    @GetMapping("/sse")
    public SseEmitter sseEmitter() {
      return sseEmitter;
    }
  }

  @Controller
  class IndexController {
    @GetMapping("/")
    public String index() {
      return "index";
    }
  }
}
