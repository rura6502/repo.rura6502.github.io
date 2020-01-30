package io.github.rura6502.resource_server;

import java.net.URI;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

/**
 * LoginController
 */
@RestController
public class LoginController {

  @PostMapping("/login")
  @CrossOrigin
  public ResponseEntity<Map> login(LoginInfo loginInfo) {

    RestTemplate rt = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setBasicAuth("client", "secret");

    URI uri = UriComponentsBuilder
                    .fromHttpUrl("http://localhost:8081/oauth/token")
                    .queryParam("grant_type", "password")
                    .queryParam("username", loginInfo.getUsername())
                    .queryParam("password", loginInfo.getPassword())
                    .build().toUri();

    // return rt.exchange(uri, HttpMethod.POST, new HttpEntity<T>)
    return rt.exchange(uri, HttpMethod.POST, new HttpEntity(headers),Map.class);
  }

  @PostMapping("/refresh")
  @CrossOrigin
  public ResponseEntity<Map> refresh(String refresh) {
    RestTemplate rt = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setBasicAuth("client", "secret");

    URI uri = UriComponentsBuilder
                    .fromHttpUrl("http://localhost:8081/oauth/token")
                    .queryParam("grant_type", "refresh_token")
                    .queryParam("refresh_token", refresh)
                    .build().toUri();

    // return rt.exchange(uri, HttpMethod.POST, new HttpEntity<T>)
    return rt.exchange(uri, HttpMethod.POST, new HttpEntity(headers),Map.class);
  }


  
}

@Data
class LoginInfo {
  private String username;
  private String password;
}