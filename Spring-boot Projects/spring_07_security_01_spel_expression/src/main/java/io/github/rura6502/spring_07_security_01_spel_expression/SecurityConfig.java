package io.github.rura6502.spring_07_security_01_spel_expression;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SecurityConfig
 * hasRole / hasAuthority
 * hasAnyRoles / hasAnyAuthorities
 * hasIpAddress
 * principal
 * Authentication
 * permitAll
 * denyAll
 * isAnonymous()
 * isRemberMe()
 * isAuthenticated()
 * isFullyAuthenticated()
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
    .withUser("aaa").password("aaa1234").roles("A")
    .and()
    .withUser("bbb").password("bbb1234").roles("B")
    .and()
    .withUser("ccc").password("ccc1234").authorities("ROLE_C") 
    ;
    // role은 자동으로 앞에 'ROLE_' 이라는 prefix가 추가됨.
    // authority는 문자 그대로를 사용함.
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
    .formLogin()
    .and()
    // .authorizeRequests().anyRequest().authenticated()
    .authorizeRequests()
    .antMatchers("/a").hasRole("A")
    .antMatchers("/b").access("hasRole('A') or hasRole('B') or hasRole('C')")
    // ==> hasAnyRole('A', 'B', 'C')
    .antMatchers("/c").hasAuthority("ROLE_C")
    .anyRequest().authenticated()
    ;
    // hasRole 의 경우 'ROLE_'이라는 prefix를 자동으로 붙여서 비교함.
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}