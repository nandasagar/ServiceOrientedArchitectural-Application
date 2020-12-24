package com.mobile.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;
import com.mobile.application.repository.ItemRepository;
import com.mobile.application.model.Item;

@Controller
@RequestMapping("/User")
@ResponseBody
public class SearchController {

	@Autowired
	private ItemRepository itemRepo;

	/**
	 * Searching Item Operations
	 * 
	 * @param search_Item
	 * @param user
	 * @return
	 */
	@ResponseBody
	@GetMapping("/search/{searchItem}/{page}")
	public Page<Item> searchItemOpr(@PathVariable String searchItem, @PathVariable Integer page) {
		Page<Item> items = null;
		Page<Item> pages = null;

		Pageable pageable = PageRequest.of(page, 100, Sort.by("model").ascending());

		items = itemRepo.findAll(pageable);
		List<Item> item = items.getContent();
		List<Item> res = new ArrayList<Item>();
		for (var list : item) {
			System.out.println("Starts");
			if ((list.getItemname().contains(searchItem.toUpperCase()))
					|| (list.getItemtype().contains(searchItem.toLowerCase()))) {
				res.add(list);
			}
			System.out.println(res);
		}
		pages = new PageImpl<>(res);
		return Objects.nonNull(pages) ? pages : new PageImpl<Item>(new ArrayList<Item>());
	}

}