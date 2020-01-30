package io.github.rura6502.tcp_to_websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
  }

  @Autowired
  StreamSocketServerHandler handler;
  
  @Bean
  CommandLineRunner runner() {
    return args -> {
      NettyServer server = new NettyServer(5555, handler);
      server.run();
    };
  }

}
