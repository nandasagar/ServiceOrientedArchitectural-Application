package com.mobile.application.service;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mobile.application.model.Orders;
import com.mobile.application.model.Payment;
import com.mobile.application.repository.PaymentRepository;

@Service
@Transactional

public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepo;

	public PaymentService(PaymentRepository payRepo) {
		
		this.paymentRepo = payRepo;
	} 

public Payment savePayment(Payment payment,Orders order) {
	
	/*
	 * payment.setEmail(order.getEmail()); payment.setOrderid(order.getOrderid());
	 * payment.setTotal(order.getTotal());
	 */
		return paymentRepo.save(payment);
		
	}
}


