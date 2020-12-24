package com.mobile.application.controller;



	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpSession;

	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.RequestMapping;

	/**
	 * Admin Logout Cotroller
	 * @author Nanda sagar
	 *
	 */
	@Controller
	@RequestMapping("/Admin") 
	public class AdminLogoutController {

		
		/**
		 * Ending Admin Session 
		 * @param request
		 * @return
		 */
		@RequestMapping("/adminlogout")
		public String closeSession(HttpServletRequest request){
		    HttpSession session=request.getSession();  
		    session.invalidate();  
		    System.out.println("Session closed ");
		    return "/home";
		}//closeSessione

		
	}
