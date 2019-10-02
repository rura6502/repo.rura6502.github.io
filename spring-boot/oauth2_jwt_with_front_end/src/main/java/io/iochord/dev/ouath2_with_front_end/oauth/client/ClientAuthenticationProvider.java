package io.iochord.dev.ouath2_with_front_end.oauth.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * CustomAuthenticationProvider
 */
public class ClientAuthenticationProvider implements AuthenticationProvider {

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    return new UsernamePasswordAuthenticationToken(
      authentication.getPrincipal()
      , authentication.getCredentials()
      , authentication.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return true;
  }

  
}