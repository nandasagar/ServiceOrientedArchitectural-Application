
package com.mobile.application.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.mobile.application.model.User;
import com.mobile.application.repository.UserRepository;


@SpringBootTest
 public class LoginControllerTests {
  
  @Autowired
 private UserRepository userRepository;
  
  
  @Test
  public void loginUser() {
  
  String email="loki@gmail.com"; String password="123"; 
  User userList =userRepository.findByEmailAndPassword(email,password);
  
  assertThat(userList.getEmail()) .isEqualTo(email) ;
  assertThat(userList.getPassword()) .isEqualTo(password)  ; 
  
  }
  
 
  }
 