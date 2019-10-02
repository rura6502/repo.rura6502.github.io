package io.github.rura6502.authorization_server.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import io.github.rura6502.authorization_server.user.User;
import io.github.rura6502.authorization_server.user.UserRepository;
import lombok.AllArgsConstructor;

/**
 * CustomUserDetailsService
 */
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    
    User user = userRepository.findOneByLoginUsername(username);

    if (user != null)
      return new CustomUserDetails(user);
    else 
      throw new UsernameNotFoundException("username : " + username);
  }

  
}