package io.github.rura6502.authorization_server.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.github.rura6502.authorization_server.user.User;
import lombok.AllArgsConstructor;

/**
 * CustomUserDetails
 */
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

  private final User user;
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(user.getGrant().name()));
  }

  @Override
  public String getPassword() {
    return user.getLoginPassword();
  }

  @Override
  public String getUsername() {
    return user.getLoginUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  
}