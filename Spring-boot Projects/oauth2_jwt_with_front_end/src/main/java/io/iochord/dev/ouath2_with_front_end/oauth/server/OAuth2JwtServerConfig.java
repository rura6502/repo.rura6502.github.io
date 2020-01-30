package io.iochord.dev.ouath2_with_front_end.oauth.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * AuthorizationServerConfig
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
// @EnableConfigurationProperties(JwtProperties.class)
public class OAuth2JwtServerConfig extends AuthorizationServerConfigurerAdapter {

  @NonNull
  PasswordEncoder passwordEncoder;

  @NonNull
  JwtAccessTokenConverter jwtAccessTokenConverter;

  @NonNull
  TokenStore tokenStore;


  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.passwordEncoder(passwordEncoder)
                  .tokenKeyAccess("permitAll()")
                  .checkTokenAccess("isAuthenticated()")
                  ;
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
    .withClient("client")
    .secret("1234")
    .authorizedGrantTypes("password", "refresh_token", "authorization_code")
    .scopes("read", "write")
    .accessTokenValiditySeconds(99999)
    ;
  }

  @Autowired
  @Qualifier("inMemoryUserDetailsService")
  UserDetailsService userDetailsService;

  @Autowired
  @Qualifier("authorizationServerAuthenticationManager")
  AuthenticationManager authenticationManager;

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
    .authenticationManager(authenticationManager)
    .accessTokenConverter(this.jwtAccessTokenConverter)
    .userDetailsService(userDetailsService)
    .tokenStore(tokenStore)
    ;
  }

  


}