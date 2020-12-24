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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mobile.application.model.Cart;
import com.mobile.application.model.Item;
import com.mobile.application.model.Orders;
import com.mobile.application.model.Payment;
import com.mobile.application.model.User;
import com.mobile.application.repository.CartRepository;
import com.mobile.application.repository.ItemRepository;
import com.mobile.application.repository.OrdersRepository;
import com.mobile.application.repository.PaymentRepository;
import com.mobile.application.repository.UserRepository;

@Controller
@RequestMapping("/User")
@ResponseBody
public class OrdersController {

	@Autowired
	private OrdersRepository orderRepository;
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Value("${item_size}")
	private int size;

	/**
	 * Saves Users Orders from cart
	 * 
	 * @param models
	 * @param users
	 * @return
	 */

	// 1st method using Request Body

	@PostMapping(value = "/saveOrders/{id}/{page}")
	public Page<Orders> saveOrders(@PathVariable Integer id, @PathVariable Integer page) {
		Page<Orders> orders = null;
		User userList = userRepository.findById(id);
		String email = null;
		email = userList.getEmail();
		Pageable pageab = PageRequest.of(page, 10, Sort.by("cartid"));

		Page<Cart> cartList = cartRepository.findAllById(id, pageab);
		List<Cart> cart = cartList.getContent();

		System.out.println(cart);
		for (var iterate : cart) {
			Orders newOrder = new Orders(id, email, iterate.getModel(), iterate.getItemname(), iterate.getQuantity(),
					iterate.getTotal(), "IN");
			orderRepository.save(newOrder);
			cartRepository.deleteById(iterate.getCartid());
			Item item = itemRepository.findById(iterate.getModel()).get();
			item.setQuantity_available(item.getQuantity_available() - iterate.getQuantity());
			itemRepository.save(item);
		}
		Pageable pageable = PageRequest.of(page, size, Sort.by("orderid").descending());
		orders = orderRepository.findAllOrdersById(id, pageable);

		return Objects.nonNull(orders) ? orders : new PageImpl<Orders>(new ArrayList<Orders>());
	}

	/**
	 * Removes Users Specified Orders
	 * 
	 * @param orderid
	 * @param user
	 * @return
	 */
	@PostMapping("/removeOrder/{id}/{orderid}/{page}")
	public Page<Orders> remove(@PathVariable Integer id, @PathVariable("orderid") int orderid,
			@PathVariable Integer page) {

		Page<Orders> orders = null;

		Pageable pageable = PageRequest.of(page, size, Sort.by("orderid").descending());
		orderRepository.deleteById(orderid);
		orders = orderRepository.findAllOrdersById(id, pageable);

		return Objects.nonNull(orders) ? orders : new PageImpl<Orders>(new ArrayList<Orders>());
	}

	/**
	 * All yet to Complete Orders Every Order
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/getAllOrder/{id}/{page}")
	public Page<Orders> getEveryOrder(@PathVariable Integer id, @PathVariable Integer page) {
		Page<Orders> newOrders = null;

		Pageable pageable = PageRequest.of(page, size, Sort.by("orderid").descending());
		newOrders = orderRepository.findAllOrdersById(id, pageable);

		return Objects.nonNull(newOrders) ? newOrders : new PageImpl<Orders>(new ArrayList<Orders>());
	}

	/**
	 * Displays All successful orders
	 * 
	 * @param model
	 * @param users
	 * @return
	 */

	@GetMapping(value = "/getorder/{id}/{page}")
	public Page<Payment> getOrderList(@PathVariable Integer id, @PathVariable Integer page) {
		Page<Payment> newOrders = null;

		Pageable pageable = PageRequest.of(page, 3, Sort.by("paymentid").descending());
		newOrders = paymentRepository.findAllOrdersById(id, pageable);

		return Objects.nonNull(newOrders) ? newOrders : new PageImpl<Payment>(new ArrayList<Payment>());
	}

}
