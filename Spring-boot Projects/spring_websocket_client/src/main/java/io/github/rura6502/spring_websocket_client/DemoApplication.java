package io.github.rura6502.spring_websocket_client;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  CommandLineRunner runner() {
    return args -> {

      WebSocketContainer container = ContainerProvider.getWebSocketContainer();
      container.setDefaultMaxBinaryMessageBufferSize(1024 * 1024);
      container.setDefaultMaxTextMessageBufferSize(1024 * 1024);

      StandardWebSocketClient swsc = new StandardWebSocketClient(container);
      ListenableFuture<WebSocketSession> ws = swsc.doHandshake(new CustomWebSocketHandler(), "ws://localhost:8080/api/cm/stream/live");
      ws.get().sendMessage(new TextMessage("start/5"));
    };
  }

}

class CustomWebSocketHandler implements WebSocketHandler {

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    System.out.println("afterConnectionEstablished");
  }

  @Override
  public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
    System.out.println("handleMessage : " + message.getPayloadLength());
  }

  @Override
  public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    System.out.println("handleTransportError");
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
    System.out.println("afterConnectionClosed");
  }

  @Override
  public boolean supportsPartialMessages() {
    System.out.println("supportsPartialMessages");
    return false;
  }
  
}