package io.iochord.dev.ouath2_with_front_end.oauth.server;

import java.security.KeyPair;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import io.iochord.dev.ouath2_with_front_end.oauth.JwtProperties;

/**
 * OAuth2ServerBeanConfig
 */
@Configuration
public class OAuth2JwtServerBeanConfig {

  JwtProperties jwtProperties;

  UserDetailsService userDetailsService;


  public OAuth2JwtServerBeanConfig(
    JwtProperties jwtProperties
    , UserDetailsService userDetailsService
  ) {
    this.jwtProperties = jwtProperties;
    this.userDetailsService = userDetailsService;
  }
  

  @Bean
  public JwtAccessTokenConverter jwtAccessTokenConverter() {
    JwtAccessTokenConverter jatc = new JwtAccessTokenConverter();

    Resource keyStore = new ClassPathResource(jwtProperties.getKeyStore());
    String keyStorePassword = jwtProperties.getKeyStorePassword();
    String keyPairAlias = jwtProperties.getKeyPairAlias();
    String keyPairPassword = jwtProperties.getKeyPairPassword();

    KeyStoreKeyFactory kskf = new KeyStoreKeyFactory(keyStore, keyStorePassword.toCharArray());
    KeyPair keyPair = kskf.getKeyPair(keyPairAlias, keyPairPassword.toCharArray());
    
    jatc.setKeyPair(keyPair);
    
    return jatc;
  }

  @Bean
  public TokenStore tokenStore(JwtAccessTokenConverter jatc) {
    return new JwtTokenStore(jatc);
  }

  @Bean
  ("tokenServices")
  public DefaultTokenServices tokenServices(
    TokenStore tokenStore
    , ClientDetailsService clientDetailsService
  ) {
    DefaultTokenServices tokenServices = new DefaultTokenServices();
    tokenServices.setSupportRefreshToken(true);
    tokenServices.setReuseRefreshToken(false);
    tokenServices.setTokenStore(tokenStore);
    tokenServices.setClientDetailsService(clientDetailsService);
    tokenServices.setAuthenticationManager(authenticationManager());
    return tokenServices;
  }

  @Bean("authorizationServerAuthenticationManager")
  AuthenticationManager authenticationManager() {
    AuthenticationManager auth = new ServerAuthenticationManager(userDetailsService);
    return auth;
  }
}