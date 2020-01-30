package io.iochord.rnd;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConf extends AbstractWebSocketMessageBrokerConfigurer {

  @Override
  public void configureMessageBroker(MessageBrokerRegistry reg) {
    // TODO Auto-generated method stub
    reg.enableSimpleBroker("/topic");
    reg.setApplicationDestinationPrefixes("/app");

  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry reg) {
    // TODO Auto-generated method stub
    reg.addEndpoint("/img-websocket").withSockJS();
  }
}
