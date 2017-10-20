package com.assigment.emptravel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
class HomeController {

    @ModelAttribute("module")
    String module() {
        return "home";
    }
/*
    @GetMapping("/test")
    String index(Principal principal) {
        return principal != null ? "homeSignedIn" : "homeNotSignedIn";
    }*/
    
    

	/*@RequestMapping(value={ "/","/home1"}, method = RequestMethod.GET)
	public ModelAndView home1(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home1");
		return modelAndView;
	}
	*/
}
