package com.mobile.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mobile.application.model.Item;

public interface ItemService {
	
	 
	 public List<Item> getItems();
	 public Item findById(int id);
	 public Item findByModel(Integer valueOf);
	 public Page<Item> findByItemname(String searchItem, Pageable pageable);
	 public Page<Item> findByItemtype(String brand, Pageable pageable);
  
	

}