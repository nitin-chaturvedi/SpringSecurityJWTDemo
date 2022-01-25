package com.example.SpringSecurityJWTDemo.models;

import com.example.SpringSecurityJWTDemo.models.entity.User;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Data
@NoArgsConstructor
public class MyUserDetails implements UserDetails {

  private String username;
  private String password;
  private List<GrantedAuthority> authorities;

  public MyUserDetails(User user) {
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.authorities = Arrays.stream(user.getRoles().split(","))
                                  .map(SimpleGrantedAuthority::new)
                                  .collect(Collectors.toList());
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
