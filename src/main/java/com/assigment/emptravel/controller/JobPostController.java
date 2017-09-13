package com.assigment.emptravel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.assigment.emptravel.model.Job;

@Controller
public class JobPostController {

	@RequestMapping(value="/jobpost")
	public ModelAndView postJob(){
		ModelAndView mv= new ModelAndView("jobpost");
		Job job = new Job();
		mv.addObject("job", job);
		return mv;
	}
	
}
