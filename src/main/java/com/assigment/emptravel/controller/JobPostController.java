package com.assigment.emptravel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.service.JobService;

@Controller
public class JobPostController {
	
	@Autowired
	JobService jobService;

	@RequestMapping(value="/jobpost")
	public ModelAndView job(){
		ModelAndView mv= new ModelAndView("jobpost");
		Job job = new Job();
		mv.addObject("job", job);
		return mv;
	}
	
	@RequestMapping(value="/jobpost", method = RequestMethod.POST)
	public ModelAndView createJob(@Valid Job job, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("jobpost");
		} else {
			jobService.saveJob(job);
			modelAndView.addObject("successMessage", "Job has been created successfully.");
			modelAndView.setViewName("/jobpost");
		}
		return modelAndView;
	}
	
}
