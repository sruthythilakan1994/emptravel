package com.assigment.emptravel.controller;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.assigment.emptravel.model.User;

import com.assigment.emptravel.service.UserService;

@Controller("loginController")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value={ "/","/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {	
			bindingResult
					.rejectValue("email", "error.user",
							"User with this email already exists");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully.Please <a href='/login'> login </a> with ");
			//modelAndView.addObject("user", new User());
			//modelAndView.setViewName("registration");
			modelAndView.setViewName("/registration");
			
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView home(){
		//List<Project> aaa= new ArrayList();
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		//modelAndView.addObject("projects", projectService.findAll());
		modelAndView.setViewName("/homeSignedIn");
		return modelAndView;
	}
	
	
	

}