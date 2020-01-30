// package io.iochord.dev.ouath2_with_front_end.oauth;

// import java.util.Map;
// import java.util.stream.Collectors;

// import com.fasterxml.jackson.databind.ObjectMapper;

// import org.apache.commons.io.IOUtils;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.annotation.Order;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
// import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
// import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
// import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
// import org.springframework.security.oauth2.provider.token.TokenStore;
// import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
// import org.springframework.web.client.RestClientException;
// import org.springframework.web.client.RestTemplate;
// import org.springframework.web.util.UriComponentsBuilder;

// import lombok.NonNull;
// import lombok.RequiredArgsConstructor;

// /**
//  * OAuth2JwtResourceServerConfig
//  */
// @Configuration
// @EnableResourceServer
// @RequiredArgsConstructor
// // @Order(4)
// public class OAuth2JwtResourceServerConfig extends ResourceServerConfiguration {
//     // implements ResourceServerConfigurer {

//   @NonNull
//   private TokenStore tokenStore;
  
//   @NonNull 
//   private PasswordEncoder passwordEncoder;

//   public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//     resources.tokenStore(tokenStore);
//     // resources.tokenStore(tokenStore);
//   }

//   @Override
//   public void configure(HttpSecurity http) throws Exception {
//     final String app = "/cm";
//     final String client = "client";
//     final String clientSecret = "1234";
//     final String oauth2JwtServer = "http://localhost:8080/oauth/token";
    
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

//           UserDetails principal = (UserDetails) auth.getPrincipal();
//           final String loginUsername = principal.getUsername();
//           final String password = principal.getPassword();

//           HttpHeaders headers = new HttpHeaders();
//           headers.setBasicAuth(client, clientSecret);
//           HttpEntity httpEntity = new HttpEntity(headers);
//           UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(oauth2JwtServer)
//               .queryParam("grant_type", "client_credentials")
//               .queryParam("username", loginUsername)
//               .queryParam("password", password);

//           RestTemplate restTemplate = new RestTemplate();
//           Map<String, Object> tokenInfo = null;
//           try {
//             tokenInfo = restTemplate.exchange(builder.build().toUri(), HttpMethod.POST, httpEntity, Map.class)
//                 .getBody();
//           } catch (RestClientException e) {
//             System.out.println(e.getMessage());
//           }
//           response.setHeader("token", new ObjectMapper().writeValueAsString(tokenInfo));
//         }).failureHandler((request, response, authenticationException) -> {
//           System.out.println("**failureHandler");
          
//           System.out.println("getParameter : " + request.getParameter("req_type"));
//           authenticationException.printStackTrace();
//         })
//         .and().authorizeRequests()
//                       .antMatchers(
//                         app + "/login"
//                         , "/**/**.js"
//                       ).permitAll()
//         .and().authorizeRequests().anyRequest().authenticated()
//         .and().httpBasic()
//         .and().exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/cm/login"))
//         .and().csrf().disable()
//         .cors().disable()
//         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
//     ;
//   }
  
//   private String getLoginUsername(Object principal) {
//     User result = (User) principal;
//     return result.getUsername();
//   }

//   private String getCredentails(Object principal) {
//     User result = (User) principal;
//     return result.getPassword();
//   }
// }