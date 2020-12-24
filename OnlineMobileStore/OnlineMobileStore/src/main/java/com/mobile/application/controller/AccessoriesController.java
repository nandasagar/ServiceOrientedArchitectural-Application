package com.mobile.application.controller;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mobile.application.model.Image;
import com.mobile.application.model.Item;
import com.mobile.application.repository.ItemRepository;

@Controller
@ResponseBody

public class AccessoriesController {

	@Autowired
	private ItemRepository itemRepository;

	// to add the products
	/**
	 * Saves newly added Item
	 * 
	 * @param item
	 * @return
	 */
	@PostMapping("/saveaccess")
	public String saveaccess(Item item) {

		itemRepository.save(item);
		return "accessories";
	}

	Image image;

	/**
	 * maps to Accessories page
	 * 
	 * @return
	 */
	@RequestMapping("/access")
	public String access() {
		return "/access";
	}

	/**
	 * 
	 * 
	 * maps to item->powerBank page
	 * 
	 * @return
	 */
	@GetMapping("/power/{page}")
	public Page<Item> power(@PathVariable Integer page) {
		Page<Item> product = null;

		Pageable pageable = PageRequest.of(page, 10, Sort.by("model").ascending());

		String itemdesc = "powerbank";
		product = itemRepository.findByItemtype(itemdesc, pageable);

		return Objects.nonNull(product) ? product : new PageImpl<Item>(new ArrayList<Item>());
	}

	/**
	 * maps to item->HeadSet page
	 * 
	 * @return
	 */
	@GetMapping("/headset/{page}")
	public Page<Item> headset(@PathVariable Integer page) {
		Page<Item> product = null;

		Pageable pageable = PageRequest.of(page, 10, Sort.by("model").ascending());

		String itemdesc = "headset";
		product = itemRepository.findByItemtype(itemdesc, pageable);

		return Objects.nonNull(product) ? product : new PageImpl<Item>(new ArrayList<Item>());
	}

	/**
	 * maps to item->Charger page
	 * 
	 * @return
	 */
	@GetMapping("/charger/{page}")
	public Page<Item> charger(@PathVariable Integer page) {
		Page<Item> product = null;

		Pageable pageable = PageRequest.of(page, 10, Sort.by("model").ascending());

		String itemdesc = "charger";
		product = itemRepository.findByItemtype(itemdesc, pageable);

		return Objects.nonNull(product) ? product : new PageImpl<Item>(new ArrayList<Item>());
	}

	/**
	 * maps to item->MobileCover page
	 * 
	 * @return
	 */
	@GetMapping("/cover/{page}")
	public Page<Item> cover(@PathVariable Integer page) {
		Page<Item> product = null;

		Pageable pageable = PageRequest.of(page, 10, Sort.by("model").ascending());

		String itemdesc = "mobilecover";
		product = itemRepository.findByItemtype(itemdesc, pageable);

		return Objects.nonNull(product) ? product : new PageImpl<Item>(new ArrayList<Item>());
	}

	/**
	 * maps to item->MobileScreen page
	 * 
	 * @return
	 */
	@GetMapping("/screen/{page}")
	public Page<Item> screen(@PathVariable Integer page) {
		Page<Item> product = null;

		Pageable pageable = PageRequest.of(page, 10, Sort.by("model").ascending());

		String itemdesc = "mobilescreen";
		product = itemRepository.findByItemtype(itemdesc, pageable);

		return Objects.nonNull(product) ? product : new PageImpl<Item>(new ArrayList<Item>());
	}

	/**
	 * maps to item->USB page
	 * 
	 * @return
	 */
	@GetMapping("/usb/{page}")
	public Page<Item> usb(@PathVariable Integer page) {
		Page<Item> product = null;

		Pageable pageable = PageRequest.of(page, 10, Sort.by("model").ascending());

		String itemdesc = "usb";
		product = itemRepository.findByItemtype(itemdesc, pageable);

		return Objects.nonNull(product) ? product : new PageImpl<Item>(new ArrayList<Item>());
	}

	/**
	 * maps to item->AppleMobile page
	 * 
	 * @return
	 */
	@GetMapping("/apple/{page}")
	public Page<Item> apple(@PathVariable Integer page) {
		Page<Item> product = null;

		Pageable pageable = PageRequest.of(page, 10, Sort.by("model").ascending());

		String itemdesc = "applemobile";
		product = itemRepository.findByItemtype(itemdesc, pageable);
		return Objects.nonNull(product) ? product : new PageImpl<Item>(new ArrayList<Item>());
	}

	/**
	 * maps to item->VivoMobile page
	 * 
	 * @return
	 */
	@GetMapping("/vivo/{page}")
	public Page<Item> vivo(@PathVariable Integer page) {
		Page<Item> product = null;

		Pageable pageable = PageRequest.of(page, 10, Sort.by("model").ascending());

		String itemdesc = "vivomobile";
		product = itemRepository.findByItemtype(itemdesc, pageable);
		return Objects.nonNull(product) ? product : new PageImpl<Item>(new ArrayList<Item>());
	}

	/**
	 * maps to item->RealmeMobile page
	 * 
	 * @return
	 */
	@GetMapping("/realme/{page}")

	public Page<Item> realme(@PathVariable Integer page) {
		Page<Item> product = null;

		Pageable pageable = PageRequest.of(page, 10, Sort.by("model").ascending());

		String itemdesc = "realmemobile";
		product = itemRepository.findByItemtype(itemdesc, pageable);

		return Objects.nonNull(product) ? product : new PageImpl<Item>(new ArrayList<Item>());
	}

	/**
	 * maps to item->OnePlusMobile page
	 * 
	 * @return
	 */
	@GetMapping("/oneplus/{page}")
	public Page<Item> oneplus(@PathVariable Integer page) {
		Page<Item> product = null;

		Pageable pageable = PageRequest.of(page, 10, Sort.by("model").ascending());

		String itemdesc = "oneplusmobile";

		product = itemRepository.findByItemtype(itemdesc, pageable);

		return Objects.nonNull(product) ? product : new PageImpl<Item>(new ArrayList<Item>());
	}

	/**
	 * maps to item->SamsungMobile page
	 * 
	 * @return
	 */
	@GetMapping("/samsung/{page}")
	public Page<Item> samsung(@PathVariable Integer page) {
		Page<Item> product = null;

		Pageable pageable = PageRequest.of(page, 10, Sort.by("model").ascending());

		String itemdesc = "samsungmobile";
		product = itemRepository.findByItemtype(itemdesc, pageable);
		return Objects.nonNull(product) ? product : new PageImpl<Item>(new ArrayList<Item>());
	}

	/**
	 * maps to item->RedmiMobile page
	 * 
	 * @return
	 */
	@GetMapping("/mi/{page}")
	public Page<Item> mi(@PathVariable Integer page) {
		Page<Item> product = null;

		Pageable pageable = PageRequest.of(page, 10, Sort.by("model").ascending());

		String itemdesc = "redmimobile";
		product = itemRepository.findByItemtype(itemdesc, pageable);

		return Objects.nonNull(product) ? product : new PageImpl<Item>(new ArrayList<Item>());
	}

}
