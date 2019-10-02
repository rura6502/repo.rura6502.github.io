package io.github.rura6502.authorization_server.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.github.rura6502.authorization_server.user.UserRepository;

/**
 * SecurityBeanConfig
 */
@Configuration
public class SecurityBeanConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepo) {
    return new CustomUserDetailsService(userRepo);
  }

  
}