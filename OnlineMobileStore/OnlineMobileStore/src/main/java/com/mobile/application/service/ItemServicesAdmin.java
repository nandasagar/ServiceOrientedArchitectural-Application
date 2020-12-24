package com.mobile.application.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mobile.application.model.Item;
import com.mobile.application.repository.ItemRepository;

@Service
@Transactional
public class ItemServicesAdmin {

	@Autowired
	ItemRepository itemRepo;

	public Page<Item> getAllItems(Pageable pageable) {
		return (Page<Item>) itemRepo.findAll(pageable);
	}

	public Item getItemByModel(int model) {
		return itemRepo.findById(model).get();
	}

	public void saveItem(Item item) {
		itemRepo.save(item);
	}

	public void deleteItem(int model) {
		itemRepo.deleteById(model);
	}

	

}
