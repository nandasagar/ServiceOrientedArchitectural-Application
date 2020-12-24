package com.mobile.application.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mobile.application.OnlineMobileStoreApplicationTests;
import com.mobile.application.model.Item;
import com.mobile.application.repository.ItemRepository;
import com.mobile.application.service.ItemService;

public class AccessoriesControllerTests  extends OnlineMobileStoreApplicationTests {


	
	  @Autowired
	  private ItemService service;
	  	
	  private MockMvc mockMvc;
	    
		 @Test
		  public void validateItem() {
		
			  String brand="powerbank";		

				 Pageable pageable= PageRequest.of(0, 100, Sort.by("model").ascending());
				 Page<Item> found = service.findByItemtype(brand,pageable);
				 
				 List<Item>items=found.getContent();
				 String res = null;
				 
				 for(var list:items)
				 {
						res= list.getItemtype();
				 }
				 
				 System.out.println("hello");
			  assertThat(res).isEqualTo(brand);		  
	  }
	  
	    /*	  
	     @MockBean
	    private ItemRepository repo;
	    
	    @Autowired
		private WebApplicationContext webApplicationContext;

		@Before
		public void setup() {
			mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		}

		@Test
		public void testPower() throws Exception {
			mockMvc.perform(get("/power")).andExpect(status().isOk());
					.andExpect(content().contentType("application/json;charset=UTF-8"))
					.andExpect(jsonPath("$.model").value(1019)).andExpect(jsonPath("$.itemname").value("AMBRANE"))
					.andExpect(jsonPath("$.color").value("BLACK")).andExpect(jsonPath("$.price").value(900));

		}
	  
	*/  
	
		/*
		 * @Test public void findByIdTest() {
		 * 
		 * when(repo.findById(1001)).thenReturn( Optional.of(new Item(1001,
		 * "APPLE_IPHONE_11","BLACK",55000,"128 GB","APPLE_IPHONE_11.jpg",11,1,2110,
		 * "applemobile"))); System.out.println("hello");
		 * assertEquals((1001),service.findById(1001)); }
		 */	  

}
/*
		  "model": 1019,
          "itemname": "AMBRANE",
          "color": "BLACK",
          "price": 900,
          "features": "10000 MAH",
          "image": "AMBRANE.jpg",
          "productid": 17,
          "categoryid": 2,
          "quantity_available": 1977,
          "itemtype": "powerbank"*/