package io.github.rura6502.authorization_server.oauth2;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * OAuthServerConfig
 */
@RequiredArgsConstructor
@Configuration
@EnableAuthorizationServer
public class OAuthServerConfig extends AuthorizationServerConfigurerAdapter {
  
  @NonNull
  private final AuthenticationManager authenticationManager;
  
  @NonNull
  private final DataSource dataSource;

  
  @NonNull
  private final TokenStore tokenStore;

  // @NonNull
  // private final ApprovalStore approvalStore;

  @NonNull
  private final UserDetailsService userDetailsService;

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    // super.configure(security);
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    // clients.inMemory().withClient("client")
    // .secret("secret")
    // .authorizedGrantTypes("password")
    // .authorities("ROLE_USER")
    // .scopes("read", "write", "trust")
    // ;
    clients.jdbc(dataSource);

  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
    // .tokenStore(tokenStore)
    .authenticationManager(authenticationManager)
    .tokenStore(tokenStore)
    .userDetailsService(userDetailsService)
    .reuseRefreshTokens(false)

    ;
  }

}