package com.mobile.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
	public class ApplicationController {
	/**
	 * maps Home page
	 * @return
	 */ 
	@RequestMapping("/home")
	    public String home() {
	   
	        return "home";
	    }
	    
		/**
		 * maps Login page
		 * @return
		 */
	    @RequestMapping("/login")
	    public String login() {
	   
	        return "login";
	    }
	    /**
		 * maps Users Register page
		 * @return
		 */
	    @RequestMapping("/register")
	    public String register() {
	   
	        return "register";
	    }
	    /**
		 * maps welcome page
		 * @return
		 */
	    @RequestMapping("/welcome")
	    public String welcome() {
	   
	        return "welcome";
	    }
	    /**
		 * maps Admin Login page
		 * @return
		 */
	    @RequestMapping("admin")
	    public String admin() {
	    	return "admin";
	    }
	    /**
		 * maps Admin page
		 * @return
		 */
	    @RequestMapping("adminlogin")
	    public String adminlog() {
	    	return "adminlogin";
	    }
	    
	    /**
		 * maps Cart page
		 * @return
		 */
	    @RequestMapping("/cart")
		public String cart()
		{
			return "/cart";
		}
	    
	    /**
		 * maps Contact page
		 * @return
		 */
	    @RequestMapping("/Contact")
	    public String contact() {
	   
	        return "Contact";
	    }
	    
	    /**
		 * maps Thank you page
		 * @return
		 */
	    @RequestMapping("/thanku")
	    public String thanku() {
	   
	        return "thanku";
	    }
		
		/**
		 * maps mobile page
		 * @return
		 */
		@RequestMapping("/mobile")
		public String mobile() {
			return "mobile";
		}
   }    
	    
	    
	