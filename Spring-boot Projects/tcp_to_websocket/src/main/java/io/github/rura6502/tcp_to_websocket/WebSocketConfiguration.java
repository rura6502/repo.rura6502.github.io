package io.github.rura6502.tcp_to_websocket;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * WebSocketConfiguration
 */
@Configuration
@Slf4j
public class WebSocketConfiguration implements WebSocketConfigurer {

  @Getter
  List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

  @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
      registry.addHandler(new WebSocketHandler(){
      
        @Override
        public boolean supportsPartialMessages() {
          // 핸들러 부분의 메세지 지원 여부. true 일 경우 웹 소켓 메세지를 여러 번 호출해서 받을 수 있음
          return true;
        }
      
        @Override
        public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
          // 에러 콜백
          System.out.println("handleTransportError");
          log.error("### handleTransportError, session={}, excpetion={}", session, exception);
        }
      
        @Override
        public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
          // 메세지 도착 콜백
          System.out.println("handleMessage");
          log.error("### handleMessage, session={}, message={}", session, message);
        }
      
        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
          // 사용 준비 완료 콜백
          System.out.println("afterConnectionEstablished");
          sessions.add(session);
          log.error("### afterConnectionEstablished session={}", session);
        }
      
        @Override
        public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
          // 접속 닫히고 콜백
          System.out.println("afterConnectionClosed");
          log.error("### afterConnectionClosed session={}, closeStatus={}", session, closeStatus);
        }
      }, "/websocket-test").setAllowedOrigins("*");
    }
}