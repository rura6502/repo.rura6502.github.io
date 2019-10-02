package io.iochord.dev.ouath2_with_front_end.oauth.server;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * CustomAuthenticationManager
 */
@RequiredArgsConstructor
public class ServerAuthenticationManager implements AuthenticationManager {

  @NonNull
  UserDetailsService userDetailsService;

  

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    final String inputUsername = authentication.getPrincipal().toString();
    final String inputPassword = (String) authentication.getCredentials();

    User user = null;

    try {
      user = (User) userDetailsService.loadUserByUsername(inputUsername);
      final String realPassword = user.getPassword();
      if (!inputPassword.equals(realPassword)) {
        throw new BadCredentialsException("User Authorization was failed");
      }
    } catch (UsernameNotFoundException ex) {
      throw new BadCredentialsException("User Authorization was failed");
    }

		return new UsernamePasswordAuthenticationToken(
      user.getUsername()
      , user.getPassword()
      , user.getAuthorities()
    );
	}

  
}