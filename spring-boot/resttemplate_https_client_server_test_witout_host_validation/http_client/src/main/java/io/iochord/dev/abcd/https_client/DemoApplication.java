package io.iochord.dev.abcd.https_client;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  CommandLineRunner runner() {
    return args -> {
      SSLContextBuilder sslContext = new SSLContextBuilder();
      sslContext.loadTrustMaterial(null, new TrustSelfSignedStrategy());
      HttpClient httpClient = HttpClients.custom()
      
      .setSslcontext(sslContext.build()).setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();

      HttpComponentsClientHttpRequestFactory requestFactory =
              new HttpComponentsClientHttpRequestFactory();
      
      requestFactory.setHttpClient(httpClient);
      
      RestTemplate restTemplate = new RestTemplate(requestFactory);

      String uri = "https://localhost:8080";
      ResponseEntity<String> entity = restTemplate.getForEntity(uri, String.class);
      System.out.println(entity.getBody());
    };
  }

}
