package com.assigment.emptravel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.assigment.emptravel.model.JobApplication;
import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.User;
import com.assigment.emptravel.service.ApplicationService;
import com.assigment.emptravel.service.JobService;
import com.assigment.emptravel.service.UserService;
import com.assigment.emptravel.util.Util;

@Controller
public class ApplicationController {
	
	@Autowired
	JobService jobService;
	
	@Autowired 
	UserService userService;
	
	@Autowired
	ApplicationService applicationService;

	@Autowired
	Util util;
	
	
	@RequestMapping(value="/jobpost/apply", method = RequestMethod.POST)
	public ModelAndView applyJob(Job job, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		jobService.applyJob(user, jobService.findById(job.getId()));
		
		modelAndView.addObject("successMessage", "Job Applied successfully.");
		modelAndView.setViewName("/jobapply");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	
	
	@RequestMapping(value="/appliedjobs/{id}", method = RequestMethod.GET)
	public ModelAndView viewJobStatus( @PathVariable int id){
		
		ModelAndView modelAndView = new ModelAndView();
		JobApplication application = jobService.getApplication(id);
		modelAndView.addObject("job", application.getJob()); 
		modelAndView.setViewName("/appliedJobStatus");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	
	
	@RequestMapping(value="/jobapplications", method = RequestMethod.GET)
	public ModelAndView approveApplication(){
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pending", jobService.getAllPendingApplication());
		modelAndView.addObject("approved", jobService.getAllApprovedApplication());
		modelAndView.addObject("rejected", jobService.getAllRejectedApplication());
		modelAndView.addObject("role", util.getRole());
		modelAndView.setViewName("/jobapplications");
		return modelAndView;
		
	}
	
	
	@RequestMapping(value="/pendingview/{id}", method = RequestMethod.GET)
	public ModelAndView actonapplictaion( @PathVariable int id){
		ModelAndView modelAndView = new ModelAndView();
		JobApplication application = jobService.getApplication(id);
		modelAndView.addObject("app", application);
		modelAndView.addObject("role", util.getRole());
		modelAndView.setViewName("/pendingview");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/pendingaction", method = RequestMethod.POST)
	public ModelAndView approveOrRejectApplication(JobApplication app, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		JobApplication appToBeUpdated = applicationService.findApplicationById(app.getId());
		appToBeUpdated.setStatus(app.getStatus());
		appToBeUpdated.setMessage(app.getMessage());
		
		
		
		
		applicationService.saveApplication(appToBeUpdated);
		modelAndView.addObject("successMessage", "Updated successfully.");
		modelAndView.addObject("app", appToBeUpdated);
		modelAndView.addObject("role", util.getRole());
		modelAndView.setViewName("/pendingview");
		
		
		return modelAndView;
	}
	
	/*@RequestMapping(value="/skillset",method = RequestMethod.GET)
	public ModelAndView skillSet{
		ModelAndView modelAndView = new ModelAndView();
		//User user = userService.findUserById( id);
		modelAndView.addObject("skill",skill );
		modelAndView.setViewName("/skillset");
	
		return modelAndView;
	*/
	
	}	
	
	
	



