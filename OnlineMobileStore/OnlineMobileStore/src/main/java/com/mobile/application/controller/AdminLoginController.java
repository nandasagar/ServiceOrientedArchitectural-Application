package com.mobile.application.controller;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.application.model.User;
import com.mobile.application.repository.UserRepository;

/**
 * Admin Login Controller
 * 
 * @author Nanda sagar
 *
 */
//@SessionAttributes("Admin")
@Controller
@ResponseBody
public class AdminLoginController {

	/**
	 * Admin Session Setup
	 * 
	 * @return
	 */
	@ModelAttribute("Admin")
	public User setUp() {
		return new User();
	}

	@Autowired
	private UserRepository userRepository;

	/**
	 * maps to admin register page
	 * 
	 * @return
	 */
	@RequestMapping("register1")
	public String register1() {
		return "register1";
	}

	/**
	 * creating post mapping that post the new user detail in the database
	 * 
	 * @param users
	 * @return
	 */
	@PostMapping("/saveAdmin")
	private int saveAdmin(@RequestBody User users) {
		String email = users.getEmail();
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if (email.matches(regex) && users.getRolename().equals("Admin")) {
			userRepository.save(users);
		}

		return users.getId();
	}

	/**
	 * creating put mapping that updates the Admin details
	 * 
	 * @param users
	 * @return
	 */
	@PutMapping("/updateAdmin")
	private User updateAdmin(@RequestBody User users) {
		String email = users.getEmail();
		User user = null;
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if (email.matches(regex)) {
			user = userRepository.save(users);
		}
		return user;
	}

	/**
	 * Validates Admin Credentials
	 * 
	 * @param user
	 * @param bindingResult
	 * @param request
	 * @return
	 */
	@PostMapping("/validateAdmin/{email}/{password}")
	public User LoginAdmin(@PathVariable String email, @PathVariable String password) {

		User userList = null;
		try {
			userList = userRepository.findByEmailAndPassword(email, password);
			System.out.println("Before " + userList);

			if (userList.getRolename().equals("Admin")) {
				return Objects.nonNull(userList) ? userList : new User();
			}
		} catch (Exception exception) {
			System.out.println("Exception in Search User" + exception);
		}
		return Objects.nonNull(userList) ? userList : new User();
	}
}