package com.mobile.application.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import com.mobile.application.model.Payment;
@EnableJpaRepositories
public interface PaymentRepository extends JpaRepository<Payment,Integer > {

	List<Payment> findAllByEmail(String email);

	List<Payment> findAllById(int id);

	Page<Payment> findAllOrdersById(Integer id, Pageable pageable);

}
