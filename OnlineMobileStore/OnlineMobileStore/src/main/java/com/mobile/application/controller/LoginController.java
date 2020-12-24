package com.mobile.application.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.application.model.User;
import com.mobile.application.repository.UserRepository;

@Controller
@ResponseBody
public class LoginController {

	/**
	 * Session Instantiation
	 * 
	 * @return
	 */
	@ModelAttribute("User")
	public User setUp() {
		return new User();
	}

	@Autowired
	private UserRepository userRepository;

	/**
	 * creating post mapping that post the new user detail in the database
	 * 
	 * @param users
	 * @return
	 */
	@PostMapping("/saveUsers")
	private int saveUser(@RequestBody User users) {
		userRepository.save(users);
		return users.getId();
	}

	/**
	 * creating put mapping that updates the user detail
	 * 
	 * @param users
	 * @return
	 */

	@PutMapping("/updateUsers")
	private User updateUser(@RequestBody User users) {
		String email = users.getEmail();
		User user = null;
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if (email.matches(regex)) {
			user = userRepository.save(users);
		}
		return user;
	}

	/**
	 * Validates on users login
	 * 
	 * @param user
	 * @param model
	 * @param request
	 * @return
	 * 
	 */
	@PostMapping("/validateuser/{email}/{password}")
	public User loginUser(@PathVariable String email, @PathVariable String password) {
		User userList = null;
		try {
			userList = userRepository.findByEmailAndPassword(email, password);
			System.out.println("Before " + userList);

			if ((userList.getPassword().equals(password))) {
				return Objects.nonNull(userList) ? userList : new User();
			}
		} catch (Exception exception) {
			System.out.println("Exception in Search User" + exception);
		}
		return Objects.nonNull(userList) ? userList : new User();
	}
}
