package com.mobile.application.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import com.mobile.application.model.User;

@EnableJpaRepositories
public interface UserRepository  extends JpaRepository<User, String> {

	User findByEmail(String email);

	Page<User> findById(Integer id, Pageable pageable);
	Page<User> findAll( Pageable pageable);

	User findById(Integer id);

	User findByEmailAndPassword(String email, String password);
}