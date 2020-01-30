package io.github.rura6502.authorization_server.oauth2;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * OAuthServerBeanConfig
 */
@Configuration
public class OAuthServerBeanConfig {

  @Bean
  public TokenStore jdbcTokenStore(DataSource ds) {
    return new JdbcTokenStore(ds);
  }

  @Bean
  public ApprovalStore approvalStore(DataSource ds) {
    return new JdbcApprovalStore(ds);
  }
}