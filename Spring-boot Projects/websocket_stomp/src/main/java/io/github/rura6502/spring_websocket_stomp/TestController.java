package io.github.rura6502.spring_websocket_stomp;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * TestController
 */
@Controller
public class TestController {

  @Autowired
  SimpMessagingTemplate template;

  
  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public Message greeting(String message) {
    return new Message(LocalDateTime.now(), "Hello", "Hello, " + message);
  }

  public TestController() {
    new Thread(() -> {
      while (true) {
        System.out.println("hi");
        template.convertAndSend("/topic/greetings", "this is test : " + LocalDateTime.now());
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      
    }).start();
    
  }
  
  
  // public void test() {
  //   return "hihi";
  // }
}