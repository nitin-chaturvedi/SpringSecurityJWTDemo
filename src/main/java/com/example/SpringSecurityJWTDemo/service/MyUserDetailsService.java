package com.example.SpringSecurityJWTDemo.service;

import com.example.SpringSecurityJWTDemo.repositories.UserRepository;
import com.example.SpringSecurityJWTDemo.models.MyUserDetails;
import com.example.SpringSecurityJWTDemo.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    //Dummy test without JPA
    //return new org.springframework.security.core.userdetails.User("username","password",new ArrayList<>());

    //In real use case fetch entity from DB and create a MyUserDetails class which implements UserDetails
    //return the object of MyUserDetails class and use the overridden methods to get rest of the details.
    Optional<User> user =userRepository.findByUsername(userName);
    user.orElseThrow(() -> new UsernameNotFoundException("Not found :: "+ userName));
    return user.map(MyUserDetails::new).get();

  }
}
