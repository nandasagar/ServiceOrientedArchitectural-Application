package com.mobile.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.application.model.Item;
import com.mobile.application.model.Orders;
import com.mobile.application.model.Payment;
import com.mobile.application.model.User;
import com.mobile.application.repository.ItemRepository;
import com.mobile.application.repository.OrdersRepository;
import com.mobile.application.repository.PaymentRepository;
import com.mobile.application.repository.UserRepository;

@Controller
@RequestMapping("/User")
@ResponseBody
public class PaymentController {

	@Autowired
	private OrdersRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Value("${item_size}")
	private int size;

	/**
	 * maps pay info page with orders details
	 * 
	 * @param users
	 * @param models
	 * @return
	 */
	@GetMapping(value = "/pay/{id}/{page}")
	public Page<Orders> getDatas(@PathVariable Integer id, @PathVariable Integer page) {

		Page<Orders> newOrders = null;
		Pageable pageable = PageRequest.of(page, size, Sort.by("orderid").descending());

		newOrders = orderRepository.findAllOrdersById(id, pageable);

		return Objects.nonNull(newOrders) ? newOrders : new PageImpl<Orders>(new ArrayList<Orders>());
	}

	/**
	 * saves Payment Informations
	 * 
	 * @param pay
	 * @param fullName
	 * @param address
	 * @param city
	 * @param modeOfPayment
	 * @param users
	 * @return
	 */

	// 1st method using request body
	@PostMapping(path = "/savePay/{id}/{page}")
	public Page<Payment> savePay(@RequestBody Payment pay, @PathVariable Integer id, @PathVariable Integer page) {

		Page<Payment> payList = null;

		User user = userRepository.findById(id);
		String email = null;
		email = user.getEmail();
		Pageable pageable = PageRequest.of(page, 100, Sort.by("orderid"));

		Page<Orders> orderList = orderRepository.findAllById(id, pageable);
		List<Orders> order = orderList.getContent();
		for (var iterate : order) {

			Payment payment = new Payment(id, iterate.getModel(), iterate.getOrderid(), pay.getFullname(), email,
					pay.getAddress(), pay.getCity(), iterate.getTotal(), iterate.getItemname(), pay.getModeofpayment());

			paymentRepository.save(payment);
			System.out.println("Done");
			Item item = itemRepository.findById(iterate.getModel()).get();
			item.setQuantity_available(item.getQuantity_available() - iterate.getQuantity());
			itemRepository.save(item);

		}
		int size = order.size();
		Pageable pagea = PageRequest.of(page, size, Sort.by("paymentid").descending());
		payList = paymentRepository.findAllOrdersById(id, pagea);

		List<Orders> orderlist = new ArrayList<Orders>();
		for (var iterate : order) {
			Orders value = orderRepository.getOne(Integer.valueOf(iterate.getOrderid()));
			orderlist.add(value);
			value.setId(0);
			orderRepository.save(value);
		}

		return Objects.nonNull(payList) ? payList : new PageImpl<Payment>(new ArrayList<Payment>());
	}

}
/*
 * Payment payment = new Payment(); payment.setAddress(pay.getAddress());
 * payment.setCity(pay.getCity()); payment.setFullname(pay.getFullname());
 * payment.setModeofpayment(pay.getModeofpayment());
 * 
 * payment.setEmail(email); payment.setId(id);
 * 
 * payment.setModel(iterate.getModel());
 * payment.setOrderid(iterate.getOrderid());
 * payment.setItemname(iterate.getItemname());
 * payment.setTotal(iterate.getTotal()); System.out.println(payment);
 * 
 * 
 * Postman : { "fullname": "NandaPaym", "address": "Dasaraii", "city":
 * "Banagalore", "modeofpayment": "cash"
 * 
 * }
 */