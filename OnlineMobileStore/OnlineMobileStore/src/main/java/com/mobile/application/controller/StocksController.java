package com.mobile.application.controller;

import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

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

import com.mobile.application.model.Item;
import com.mobile.application.repository.ItemRepository;
import com.mobile.application.repository.PaymentRepository;
import com.mobile.application.repository.UserRepository;
import com.mobile.application.service.ItemServicesAdmin;
import com.mobile.application.service.UserServicesAdmin;

@Controller
@ResponseBody
@RequestMapping("/Admin")
public class StocksController {
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

	@Autowired
	HttpServletRequest request;

	/**
	 * Stocks home
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/stockProducts/{page}")
	public Page<Item> stockProductList(@PathVariable Integer page) {
		Page<Item> products = null;

		Pageable pageable = PageRequest.of(page, size, Sort.by("model"));
		products = itemService.getAllItems(pageable);

		return Objects.nonNull(products) ? products : new PageImpl<Item>(new ArrayList<Item>());
	}

	/**
	 * maps To add new stock
	 * 
	 * @return
	 */
	@RequestMapping("/addstocks")
	public String add() {
		return "addstocks";
	}

	/**
	 * Delete Specific item in Stocks
	 * 
	 * @param model
	 * @param mod
	 * @return
	 */
	@PostMapping("/deleteStockItem/{model}/{page}")
	public Page<Item> deleteItem(@PathVariable("model") int model, @PathVariable Integer page) {
		Page<Item> products = null;

		itemService.deleteItem(model);
		Pageable pageable = PageRequest.of(page, size, Sort.by("model"));
		products = itemService.getAllItems(pageable);
		return Objects.nonNull(products) ? products : new PageImpl<Item>(new ArrayList<Item>());
	}

	/**
	 * Saves newly added Stock
	 * 
	 * @param item
	 * @param mod
	 * @return
	 */
	@PostMapping(value = "/saveStockItem/{page}")
	public Page<Item> addNewProduct(@RequestBody Item item, @PathVariable Integer page) {
		Page<Item> products = null;
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
	@PutMapping(value = "/updateStockItem/{model}/{page}")
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