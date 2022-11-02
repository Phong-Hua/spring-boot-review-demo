package com.udacity.jwdnd.c1.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.c1.review.entity.User;
import com.udacity.jwdnd.c1.review.service.UserService;

@Controller
@RequestMapping("/signup")
public class SignupController {

	private UserService userService;
	
	public SignupController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public String showSignupForm(Model theModel) {
		User user = new User();
		theModel.addAttribute("user", user);
		return "signup";
	}
	
	@PostMapping
	public String processSignup(@ModelAttribute("user") User theUser, Model theModel) {
		
		String signupError = null;

		if (!userService.isUsernameAvailable(theUser.getUsername()))
		{
			signupError = "The username already exists.";
		}
		
		if (signupError == null) {
			int result = userService.createUser(theUser);
			if (result < 0) {
				signupError = "There is something wrong with the signup. Please try again.";
			}
		}
		
		if (signupError == null) {
			theModel.addAttribute("signupSuccess", true);
		} else {
			theModel.addAttribute("signupError", signupError);
		}
		System.out.println("Signup error: " + signupError);
		return "signup";
	}
}
