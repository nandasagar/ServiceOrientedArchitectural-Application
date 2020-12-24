package com.mobile.application.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import com.mobile.application.model.Image;
import com.mobile.application.model.Item;
import com.mobile.application.model.Payment;
import com.mobile.application.model.User;
import com.mobile.application.repository.ItemRepository;
import com.mobile.application.repository.PaymentRepository;
import com.mobile.application.repository.UserRepository;
import com.mobile.application.service.ItemServicesAdmin;
import com.mobile.application.service.UserServicesAdmin;

@Controller
@RequestMapping("/Admin")
@ResponseBody
public class AdminController {
	@Autowired
	PaymentRepository paymentRepo;

	@Autowired
	ItemServicesAdmin itemService;

	@Autowired
	UserServicesAdmin userService;
	UserRepository userRepo;

	@Autowired
	ItemRepository itemRepo;
	@Value("${item_size}")
	private int size;

	Image image = new Image();

	/**
	 * Displays all Registered Users
	 * 
	 * @param mod
	 * @param users
	 * @return
	 */
	@GetMapping(value = "/users/{page}")
	public Page<User> userList(@PathVariable Integer page) {
		Page<User> user = null;

		Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

		user = userService.getAllUser(pageable);
		return Objects.nonNull(user) ? user : new PageImpl<User>(new ArrayList<User>());
	}

	/**
	 * Displays All Successful Orders made by Users
	 * 
	 * @param mod
	 * @param users
	 * @return
	 */
	@GetMapping(value = "/ordersadmin/{page}")
	public Page<Payment> orderList(@PathVariable Integer page) {
		Page<Payment> allOrders = null;

		Pageable pageable = PageRequest.of(page, size, Sort.by("paymentid").descending());

		allOrders = paymentRepo.findAll(pageable);

		return Objects.nonNull(allOrders) ? allOrders : new PageImpl<Payment>(new ArrayList<Payment>());
	}

	/**
	 * Displays all Products List
	 * 
	 * @param mod
	 * @param users
	 * @return
	 */
	@GetMapping(value = "/products/{page}")
	public Page<Item> productList(@PathVariable Integer page) {
		Page<Item> products = null;

		Pageable pageable = PageRequest.of(page, size, Sort.by("model"));
		products = itemService.getAllItems(pageable);
		return Objects.nonNull(products) ? products : new PageImpl<Item>(new ArrayList<Item>());
	}

	/**
	 * Request to Add New Product
	 * 
	 * @param users
	 * @return
	 */
	@RequestMapping("/addproduct")
	public String addP(@SessionAttribute("Admin") User users) {
		return "addproduct";
	}

	/**
	 * A product is deleted
	 * 
	 * @param model
	 * @param users
	 * @return
	 */
	@PostMapping("/delete/{model}/{page}")
	public Page<Item> deleteItem(@PathVariable("model") int model, @PathVariable Integer page) {
		Page<Item> products = null;
		itemService.deleteItem(model);
		Pageable pageable = PageRequest.of(page, size, Sort.by("model"));
		products = itemService.getAllItems(pageable);
		return Objects.nonNull(products) ? products : new PageImpl<Item>(new ArrayList<Item>());
	}

	/**
	 * New Product is Saved
	 * 
	 * @param item
	 * @param users
	 * @return
	 */
	@PostMapping(value = "/saveItem/{page}")
	public Page<Item> addNewProduct(@RequestBody Item item, @PathVariable Integer page) {
		Page<Item> products = null;
		System.out.println("Add");
		itemService.saveItem(item);
		System.out.println(this.getClass().getSimpleName() + ":=======>Item saved");
		Pageable pageable = PageRequest.of(page, size, Sort.by("model"));
		products = itemService.getAllItems(pageable);

		return Objects.nonNull(products) ? products : new PageImpl<Item>(new ArrayList<Item>());
	}

	/**
	 * A Request to Update Specific product
	 * 
	 * @param model
	 * @param users
	 * @return
	 */
	@PutMapping(value = "/update/{model}/{page}")
	public Page<Item> updateItem(@PathVariable int model, @RequestBody Item item, @PathVariable Integer page) {
		Page<Item> products = null;
		if (itemService.getItemByModel(model) != null) {
			itemService.saveItem(item);
			System.out.println("Updated Item ");
			System.out.println(this.getClass().getSimpleName() + ":=======>Item saved");
			Pageable pageable = PageRequest.of(page, size, Sort.by("model"));
			products = itemService.getAllItems(pageable);
		} else {
			System.out.println(" Item  Not Found");
			return new PageImpl<Item>(new ArrayList<Item>());
		}

		return Objects.nonNull(products) ? products : new PageImpl<Item>(new ArrayList<Item>());
	}

}
