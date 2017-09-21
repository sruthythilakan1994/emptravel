package com.assigment.emptravel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.User;
import com.assigment.emptravel.repository.JobRepository;
import com.assigment.emptravel.service.JobService;
import com.assigment.emptravel.service.UserService;

@Controller
public class JobPostController {
	
	@Autowired
	JobService jobService;
	
	@Autowired
	UserService userService;
	

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
	
	
	@RequestMapping(value="/jobpost/view/{id}", method = RequestMethod.GET)
	public ModelAndView viewJob( @PathVariable int id){
		
		ModelAndView modelAndView = new ModelAndView();
		Job job = jobService.findById(id);
		modelAndView.addObject("job", job);
		modelAndView.setViewName("/jobapply");
		return modelAndView;
		
	}
	
	@RequestMapping(value="/jobpost/edit/{id}", method = RequestMethod.GET)
	public ModelAndView updateJob( @PathVariable int id){
		ModelAndView modelAndView = new ModelAndView();
		Job job = jobService.findById(id);
		modelAndView.addObject("job", job);
		modelAndView.setViewName("/editJobpost");
		return modelAndView;
	}
	
	
	
	@RequestMapping(value="/jobpost/update", method = RequestMethod.POST)
	public ModelAndView updateJob(@Valid Job job, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("jobpost");
		} else {
			jobService.saveJob(job);
			modelAndView.addObject("successMessage", "Job has been updated successfully.");
			modelAndView.setViewName("/jobpost");
		}
		return modelAndView;
	}
		
	
	
	@RequestMapping(value="/jobpost/apply", method = RequestMethod.POST)
	public ModelAndView applyJob(Job job, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		jobService.applyJob(user, jobService.findById(job.getId()));
		
		modelAndView.addObject("successMessage", "Job has been created successfully.");
		modelAndView.setViewName("/jobpost");
		
		return modelAndView;
	}
	
	

	@RequestMapping(value="/appliedjobs/{id}", method = RequestMethod.GET)
	public ModelAndView viewJobStatus( @PathVariable int id){
		
		ModelAndView modelAndView = new ModelAndView();
		Job job = jobService.findById(id);
		modelAndView.addObject("job", job);
		modelAndView.setViewName("/appliedJobStatus");
		return modelAndView;
		
	}
	
	
	@RequestMapping(value="/jobapplications", method = RequestMethod.GET)
	public ModelAndView approveApplication(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pending", jobService.getAllPendingApplication());
		modelAndView.addObject("approved", jobService.getAllApprovedApplication());
		modelAndView.addObject("rejected", jobService.getAllRejectedApplication());
		modelAndView.setViewName("/jobapplications");
		return modelAndView;
		
	}
	
	
	@RequestMapping(value="/pendingview/{id}", method = RequestMethod.GET)
	public ModelAndView actonapplictaion( @PathVariable int id){
		
		ModelAndView modelAndView = new ModelAndView();
		Job job = jobService.findById(id);
		modelAndView.addObject("job", job);
		modelAndView.setViewName("/pendingview");
		return modelAndView;
		
	}
	
	
	@RequestMapping(value="/pendingaction", method = RequestMethod.POST)
	public ModelAndView approveOrRejectApplication(Job job, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		Job jobToBeUpdated = jobService.findById(job.getId());
		//jobService.applyJob(user, jobService.findById(job.getId()));
		
		modelAndView.addObject("successMessage", "Job has been created successfully.");
		modelAndView.setViewName("/jobpost");
		
		return modelAndView;
	}
	
	
	
	
	
	
}
