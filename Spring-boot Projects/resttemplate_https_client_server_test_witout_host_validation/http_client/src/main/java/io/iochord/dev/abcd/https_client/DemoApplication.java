package io.iochord.dev.abcd.https_client;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
    // doHttp();
    doHttps();
  }

  // @Bean
  // CommandLineRunner runner() {
  //   return args -> {
  //     doHttp();
  //   };
  // }

  static void doHttps() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
    SSLContextBuilder sslContext = new SSLContextBuilder();
      sslContext.loadTrustMaterial(null, new TrustSelfSignedStrategy());
      HttpClient httpClient = HttpClients.custom()
      
      .setSslcontext(sslContext.build()).setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();

      HttpComponentsClientHttpRequestFactory requestFactory =
              new HttpComponentsClientHttpRequestFactory();
      
      requestFactory.setHttpClient(httpClient);
      
      RestTemplate restTemplate = new RestTemplate(requestFactory);

      // String uri = "https://localhost:8080";
      String uri = "https://192.168.55.160:8080";
      ResponseEntity<String> entity = restTemplate.getForEntity(uri, String.class);
      System.out.println(entity.getBody());
  }

  static void doHttp() {
    RestTemplate restTemplate = new RestTemplate();

    // String uri = "https://localhost:8080";
    String uri = "https://192.168.55.160:8080";
    ResponseEntity<String> entity = restTemplate.getForEntity(uri, String.class);
    System.out.println(entity.getBody());
  }

}
