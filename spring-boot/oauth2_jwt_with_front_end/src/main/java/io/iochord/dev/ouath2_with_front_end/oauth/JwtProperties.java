package io.iochord.dev.ouath2_with_front_end.oauth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import lombok.Data;

/**
 * JwtProperties
 */
@Configuration
@PropertySource("classpath:jwt.properties")
@ConfigurationProperties
@Data
public class JwtProperties {

  private String keyStore;
  private String keyStorePassword;
  private String keyPairAlias;
  private String keyPairPassword;
}