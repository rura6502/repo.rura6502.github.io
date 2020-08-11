package io.github.rura6502.tcp_to_websocket;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableWebSocket
@Slf4j
public class DemoApplication {

  public static void main(String[] args) {

    // new UdpNetty(5555, new UdpHandler(new H264NALProcessor(bytes -> {
    //   System.out.println(bytes.length);
    // }))).run();;


    SpringApplication.run(DemoApplication.class, args);
  }

  @Autowired
  TcpHandler tcpHandler;

  // @Autowired
  // UdpHandler udpHandler;
  @Autowired
  WebSocketConfiguration wsConf;

  @Bean
  CommandLineRunner runner() {
    return args -> {
      System.out.println(formatSize(Runtime.getRuntime().totalMemory()));
      System.out.println(formatSize(Runtime.getRuntime().maxMemory()));
      System.out.println(formatSize(Runtime.getRuntime().freeMemory()));
      // TcpNetty server = new TcpNetty(5555, tcpHandler);
      // UdpNetty server = new UdpNetty(5555, new UdpHandler(bytes -> {
        
      //   wsConf.getSessions().parallelStream().forEach(session -> {
      //   try {
      //     session.sendMessage(new BinaryMessage(bytes));
      //   } catch (IOException e) {
      //     return;
      //   }
      // });
      // }));
      UdpNetty server = new UdpNetty(5556, new UdpHandler(new H264NALProcessor(bytes -> {
        // System.out.println(bytes.length);
        wsConf.getSessions().parallelStream().forEach(session -> {
          try {
            session.sendMessage(new BinaryMessage(bytes));
          } catch (IOException e) {
            log.error("error = {}", e.getMessage());
            return;
          }
        });
      })));
      server.run();
    };
  }

  public static String formatSize(long v) {
    if (v < 1024) return v + " B";
    int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
    return String.format("%.1f %sB", (double)v / (1L << (z*10)), " KMGTPE".charAt(z));
}

  @RestController
  class TestController {

    @GetMapping("/send/{message}")
    public void send(@PathVariable("message") String message) {
      wsConf.getSessions().parallelStream().forEach(session -> {
        try {
          session.sendMessage(new BinaryMessage(message.getBytes()));
        } catch (IOException e) {
          log.error("Failed to send message...");
        }
      });
    }
  }

}
