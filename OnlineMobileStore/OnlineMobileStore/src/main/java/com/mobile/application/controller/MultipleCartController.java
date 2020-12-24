
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.application.error.ItemNotfoundException;
import com.mobile.application.model.Cart;
import com.mobile.application.model.Item;
import com.mobile.application.model.User;
import com.mobile.application.repository.CartRepository;
import com.mobile.application.repository.ItemRepository;
import com.mobile.application.repository.UserRepository;

@Controller
@RequestMapping("/User")
@ResponseBody
public class MultipleCartController {
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	@Value("${item_size}")
	private int size;

	/**
	 * maps particular Item
	 * 
	 * @param modelType
	 * @return
	 */

	@GetMapping(value = "/addcart/{modelType}")
	public Item getData(@PathVariable("modelType") int modelType) {
		
		if(itemRepository.findByModel(modelType) == null)throw new ItemNotfoundException();

		Item  itemdta = itemRepository.findByModel(modelType);
		
		return Objects.nonNull(itemdta) ? itemdta : new Item();
	}

	/**
	 * saves cart details
	 * 
	 * @param id
	 * @param cart
	 * @param model
	 * @param price
	 * @param qty
	 * @param total
	 * @return
	 */
	// 1st method using RequestBody of Cart
	@PostMapping(value = "/saveCart/{page}")
	public Page<Cart> saveToCart(@RequestBody Cart cart, @PathVariable Integer page) {

		Item itemdta = itemRepository.findByModel(cart.getModel());
		System.out.println("frst********************************" + itemdta);
		Page<Cart> newCart = null;

		if (itemdta.getQuantity_available() < cart.getQuantity()) {
			return (new PageImpl<Cart>(new ArrayList<Cart>()));
		}
		User userList = userRepository.findById(cart.getId());
		if (userList == null)
			return new PageImpl<Cart>(new ArrayList<Cart>());
		Pageable pageable = PageRequest.of(page, 15, Sort.by("cartid").descending());
		System.out.println(cartRepository.save(cart));
		newCart = cartRepository.findAllById(cart.getId(), pageable);
		return Objects.nonNull(newCart) ? newCart : new PageImpl<Cart>(new ArrayList<Cart>());
	}

	
	// fine
	/**
	 * All cart values of particular user
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/getcart/{id}/{page}")
	public Page<Cart> getCartAll(@PathVariable Integer id, @PathVariable Integer page) {

		Page<Cart> cart = null;

		Pageable pageable = PageRequest.of(page, size, Sort.by("cartid").descending());
		cart = cartRepository.findAllById(id, pageable);
		return Objects.nonNull(cart) ? cart : new PageImpl<Cart>(new ArrayList<Cart>());
	}

	// okay
	/**
	 * Removing user desired product from cart
	 * 
	 * @param id
	 * @param cartid
	 * @return
	 */

	@PostMapping("/remove/{id}/{cartid}/{page}")
	public Page<Cart> remove(@PathVariable Integer id, @PathVariable("cartid") int cartid, @PathVariable Integer page) {

		Page<Cart> carte = null;
		Pageable pageable = PageRequest.of(page, size, Sort.by("cartid").descending());
		cartRepository.deleteById(cartid);
		System.out.println("Deleted");
		carte = cartRepository.findAllById(id, pageable);

		return Objects.nonNull(carte) ? carte : new PageImpl<Cart>(new ArrayList<Cart>());
	}
}
/*
 * postman :savecart {
 * 
 * 
 * "email": "nandasagar55@gmail.com", "model": 1007, "quantity": 1, "price":
 * 10000, "total": 10000, "itemname": "REDMI_9A", "id": 866 }
 */