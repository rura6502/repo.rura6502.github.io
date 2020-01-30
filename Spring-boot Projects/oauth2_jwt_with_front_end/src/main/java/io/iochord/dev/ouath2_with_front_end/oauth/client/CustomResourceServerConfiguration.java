package io.iochord.dev.ouath2_with_front_end.oauth.client;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;

/**
 * ResourceServerConfig
 */
@Configuration
// @EnableResourceServer
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true)
public class CustomResourceServerConfiguration extends ResourceServerConfiguration {

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    final String app = "/cm";
    final String client = "client";
    final String clientSecret = "1234";
    final String oauth2JwtServer = "http://localhost:8080/oauth/token";
    final String ROOT_PATTERN = "/**";

    http.formLogin().loginPage(app + "/login").loginProcessingUrl(app + "/login").successHandler((req, res, auth) -> {

      final String loginUsername = auth.getPrincipal().toString();
      final String loginPassword = auth.getCredentials().toString();

      HttpHeaders headers = new HttpHeaders();
      headers.setBasicAuth(client, clientSecret);
      HttpEntity httpEntity = new HttpEntity(headers);
      UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(oauth2JwtServer)
          .queryParam("grant_type", "password").queryParam("username", loginUsername)
          .queryParam("password", loginPassword);

      RestTemplate restTemplate = new RestTemplate();
      Map<String, Object> tokenInfo = null;
      try {
        tokenInfo = restTemplate.exchange(builder.build().toUri(), HttpMethod.POST, httpEntity, Map.class).getBody();
      } catch (RestClientException e) {
        System.out.println(e.getMessage());
      }
      res.setHeader("token", new ObjectMapper().writeValueAsString(tokenInfo));
    }).failureHandler((req, res, ex) -> {
      System.out.println("authentication was failed");
    }).and().authorizeRequests().antMatchers(app + "/login", "/**/**.js").permitAll()
    .and().authorizeRequests().anyRequest().authenticated()
    .and().httpBasic().and().csrf().disable().sessionManagement()
    .sessionCreationPolicy(SessionCreationPolicy.NEVER);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider()).inMemoryAuthentication().passwordEncoder(passwordEncoder())
        .withUser("admin").password("1234").authorities("ROLE_ADMIN").and().and().eraseCredentials(false);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    super.configure(web);
  }

  @Bean("resourceServerAuthProvider")
  public AuthenticationProvider authenticationProvider() {
    return new ClientAuthenticationProvider();
  }

  @Bean("inMemoryUserDetailsService")
  @Override
  public UserDetailsService userDetailsServiceBean() throws Exception {
    return super.userDetailsServiceBean();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}