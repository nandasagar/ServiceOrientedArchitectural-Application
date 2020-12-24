package com.mobile.application.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mobile.application.model.Item;

@EnableJpaRepositories
public interface ItemRepository extends JpaRepository<Item, Integer>{


	Page<Item> findByItemtype(String itemdesc, Pageable pageable);
	Page<Item> findAllOrdersByModel(Integer model, Pageable pageable);
	Item findByModel(int modelType);
	Item findByItemtype(String brand);

	boolean model(int modelType);
}
