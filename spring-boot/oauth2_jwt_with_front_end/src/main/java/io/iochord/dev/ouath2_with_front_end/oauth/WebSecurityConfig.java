// package io.iochord.dev.ouath2_with_front_end.oauth;

// import org.apache.commons.io.IOUtils;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.annotation.Order;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

// import lombok.RequiredArgsConstructor;

// /**
//  * WebSecurityConfig
//  */
// @Configuration
// @EnableWebSecurity
// @RequiredArgsConstructor
// @EnableGlobalMethodSecurity(prePostEnabled = true)
// // @Order(1)
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


//   @Override
//   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//     auth
//     .inMemoryAuthentication()
//     .passwordEncoder(passwordEncoder())
//     .withUser("admin").password("1234")
//     .authorities("ROLE_ADMIN")
//     .and().and().eraseCredentials(false);
//   }

//   @Override
//   protected void configure(HttpSecurity http) throws Exception {

//     final String app = "/cm";

//     http.formLogin().loginPage(app + "/login")
//       .loginProcessingUrl(app + "/login")
//       .successHandler((req, res, auth) -> {
        

//           System.out.println("**successHandler OAuth2JwtResourceServerConfig");

//           System.out.println(
//             IOUtils.toString(req.getReader())

//           );

//           System.out.println(
//             ((User) auth.getPrincipal()).getPassword()

//           );
//       })
//       .and().authorizeRequests()
//                   .antMatchers(
//                     app + "/login"
//                     , "/**/**.js"
//                   ).permitAll()
//     .and().authorizeRequests().anyRequest().authenticated()
//     .and().httpBasic()
//     // .and().exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/cm/login"))
//     .and().csrf().disable()
//     // .cors().disable()
//     .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
//     ;
//   }



//   @Bean
//   @Override
//   public AuthenticationManager authenticationManagerBean() throws Exception {
//     AuthenticationManager am = super.authenticationManager();
//     return am;
//   }

//   // @Override
//   // public void configure(WebSecurity web) throws Exception {

//   // }


//   @Bean("inMemoryUserDetailsService")
//   @Override
//   public UserDetailsService userDetailsServiceBean() throws Exception {
//     return super.userDetailsServiceBean();
//   }

//   @Bean
//   PasswordEncoder passwordEncoder() {
//     return NoOpPasswordEncoder.getInstance();
//   }
// }