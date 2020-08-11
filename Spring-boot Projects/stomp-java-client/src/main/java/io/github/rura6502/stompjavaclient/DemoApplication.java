package io.github.rura6502.stompjavaclient;

import java.lang.reflect.Type;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.tomcat.websocket.Constants;
import org.apache.tomcat.websocket.WsWebSocketContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
    // new DemoApplication();
    SpringApplication.run(DemoApplication.class, args);
  }

  public DemoApplication() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

    // WebSocketContainer container = ContainerProvider.getWebSocketContainer();
    // container.setDefaultMaxBinaryMessageBufferSize(1024 * 1024);
    // container.setDefaultMaxTextMessageBufferSize(1024 * 1024);

    // List<Transport> transports = new ArrayList<>(2);
    // StandardWebSocketClient standardWs = new StandardWebSocketClient(container);

    // Map<String, Object> userProperties = new HashMap<>();

    // SSLContextBuilder sslContext = new SSLContextBuilder();
    // sslContext.loadTrustMaterial(null, new TrustSelfSignedStrategy());
    // userProperties.put(Constants.SSL_CONTEXT_PROPERTY, sslContext.build());
    // standardWs.setUserProperties(userProperties);

    // transports.add(new WebSocketTransport(standardWs));
    // transports.add();
    // transports.add(new RestTemplateXhrTransport());
    


    WebSocketClient wsClient = new SockJsClient(List.of(new RestTemplateXhrTransport(httpsRestTemplate())));
    WebSocketStompClient stompClient = new WebSocketStompClient(wsClient);
    stompClient.setMessageConverter(new StringMessageConverter());
    stompClient.setInboundMessageSizeLimit(200 * 1024 * 1024);
    stompClient.connect("https://localhost:16232/api/tot/socket", new StompHanlder());

  }


  public static RestTemplate httpsRestTemplate() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
    SSLContextBuilder sslContext = new SSLContextBuilder();
    sslContext.loadTrustMaterial(null, new TrustSelfSignedStrategy());
    HttpClient httpClient = null;
      httpClient = HttpClients.custom()
      .setSslcontext(sslContext.build())
      // .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
      .setSSLHostnameVerifier(new NoopHostnameVerifier())
      .build();

    HttpComponentsClientHttpRequestFactory requestFactory =
            new HttpComponentsClientHttpRequestFactory();
    
    requestFactory.setHttpClient(httpClient);
    requestFactory.setConnectTimeout(3000);
    
    return new RestTemplate(requestFactory);
  }

}


class StompHanlder implements StompSessionHandler {

  @Override
      public Type getPayloadType(StompHeaders headers) {
        return null;
      }

      @Override
      public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println("handleFrame");
      }

      @Override
      public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println("afterConnected");
        session.subscribe("/api/tot/nmea/ais/my", new StompFrameHandler(){
        
          @Override
          public void handleFrame(StompHeaders headers, Object payload) {
            System.out.println(payload);
          }
        
          @Override
          public Type getPayloadType(StompHeaders headers) {
            return String.class;
          }
        }); 
      }

      @Override
      public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
          Throwable exception) {
        System.out.println("handleException");
      }
      @Override
      public void handleTransportError(StompSession session, Throwable exception) {
        System.out.println(exception.getMessage());
      }
}