package io.github.rura6502.security_jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * WebSecurityConfig
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {// @formatter:off
		auth.inMemoryAuthentication().withUser("john").password("123").roles("USER").and().withUser("tom")
				.password("111").roles("ADMIN");
	}// @formatter:on

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // @Bean
    // public AuthenticationManagerBuilder  authenticationManagerBuilder() {
    //   return ne
    // }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
    http.authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated().and().formLogin()
    .usernameParameter("username").passwordParameter("password").failureHandler((request, response, exception) -> {
      System.out.println(request.getAttribute("username"));
      System.out.println(request.getAttribute("password"));
    })
				.permitAll();
		// @formatter:on
    }
    

    @Bean
    public PasswordEncoder passwordEncoder() {
      return NoOpPasswordEncoder.getInstance();
    }

}
