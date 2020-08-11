package io.github.rura6502.tcp_server_to_tcp_server;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {
  
  public static void main(String[] args) {

    
    SpringApplication.run(DemoApplication.class, args);
  }

  static int sum = 0;

  @Bean
  CommandLineRunner runner() {
    return args -> {
      sendTcpServer.run();

      DemoApplication.receiveUdpServer =
      new ReceiveUdpServer(new H264NALProcessor(byteBuf -> {
        // sum += byteBuf.length;
        // System.out.println(sum);
        sendTcpServer.handler.sendMessage(byteBuf);
      }));
      DemoApplication.receiveUdpServer.run();
    };
  }

  @Autowired
  SendTcpServer sendTcpServer;

  static ReceiveUdpServer receiveUdpServer;

  @RestController
  class TestController {
    
    @GetMapping("/send/{message}")
    public void send(@PathVariable("message") String message) {
      sendTcpServer.handler.sendMessage(message);
    }

    @GetMapping("/reset/")
    public void send() {
      sum = 0;
    }
  }
}
