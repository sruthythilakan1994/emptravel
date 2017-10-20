package com.assigment.emptravel.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.assigment.emptravel.model.JobApplication;
import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.JobInfo;
import com.assigment.emptravel.model.Role;
import com.assigment.emptravel.model.User;
import com.assigment.emptravel.service.JobService;
import com.assigment.emptravel.service.UserService;
import com.assigment.emptravel.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("loginController")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);	
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	JobService jobService;

	@Autowired
	Util util;
	
	private Object applications;

	
	@RequestMapping(value={ "/","/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
	/*@RequestMapping(value={ "/","/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}*/
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration1(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration1");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	
	@RequestMapping(value = "/profile")
	public ModelAndView viewProfile() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		ModelAndView mv = new ModelAndView("profile");
		mv.addObject("role", util.getRole());
		mv.addObject("user", user);
		return mv;
	}
	
	@RequestMapping(value="/updateprofile", method = RequestMethod.GET)
	public ModelAndView updateProfile( ){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("user",user );
		modelAndView.addObject("role", util.getRole());
		modelAndView.setViewName("/updateprofile");
	
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/updateprofile", method = RequestMethod.POST)
	public ModelAndView createNewUser1(@Valid User user, BindingResult bindingResult) {
	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userExists = userService.findUserByEmail(auth.getName());
		//User userExists = userService.findUserByEmpId(user.getEmpId());
		
		//userExists.setEmpId(user.getEmpId());
		userExists.setDesignation(user.getDesignation());
		userExists.setQualification(user.getQualification());
		userExists.setExpYears(user.getExpYears());
		userExists.setName(user.getName());
		userExists.setLastName(user.getLastName());
		userExists.setEmail(user.getEmail());
		userExists.setPassword(user.getPassword());
		userExists.setAddress(user.getAddress());
		userExists.setAccountNumber(user.getAccountNumber());
		//userExists.setPhoneNumber(user.getPhoneNumber());
		userService.saveUser(userExists);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("updateprofile");
		modelAndView.addObject("role",util.getRole());
		return modelAndView;
}

	@RequestMapping(value = "/registration1", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		
		
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {	
			bindingResult
					.rejectValue("email", "error.user",
							"User with this email already exists");
			logger.warn("Triing to create user name same name ");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", bindingResult.toString());
			modelAndView.setViewName("registration1");
		} else {
			
			user.setDesignation("NA");
			user.setQualification("NA");
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully.");
			modelAndView.addObject("role",util.getRole());
			//modelAndView.addObject("successMessage", "User has been registered successfully.Please <a href='/login'> login </a> with ");
			//modelAndView.addObject("user", new User());
			//modelAndView.setViewName("registration");
			modelAndView.setViewName("/registration1");
			
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value= {"/home","/user/home","/admin/home"}, method = RequestMethod.GET)
	public ModelAndView home(){
		
		logger.info("#############################");
		logger.info("START - Home page ");
		
		//List<Project> aaa= new ArrayList();
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
	
		logger.debug("Username ="+  user.getName() );
		logger.debug("Username ="+  user.getEmail()  );
		
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.addObject("jobs", jobService.findAll());
		modelAndView.addObject("role", util.getRole());
		Set<JobApplication> applications= user.getApplications();
		List<JobInfo> appliedJobList = new ArrayList<JobInfo>();
		for ( JobApplication application : applications ){
			Job job= application.getJob();
			JobInfo jobInfo= new JobInfo();
			jobInfo.setId(application.getId());
			jobInfo.setTitle(job.getTitle());
			jobInfo.setStatus(application.getStatus());
			jobInfo.setDescription(job.getDescription());
			jobInfo.setLocation(job.getLocation());
			appliedJobList.add(jobInfo);
		}
		
		modelAndView.addObject("appliedjobs", appliedJobList);
		modelAndView.setViewName("/homeSignedIn");
		
		logger.info("User entred Home page ");
		logger.info("START - Home page ");
		
		return modelAndView;
	}
	
	
	
	@RequestMapping(value= { "/manager/home"}, method = RequestMethod.GET)
	public ModelAndView managerhome(){
		
		logger.info("#############################");
		logger.info("START - Home page ");
		
		//List<Project> aaa= new ArrayList();
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
	
		logger.debug("Username ="+  user.getName() );
		logger.debug("Username ="+  user.getEmail()  );
		
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.addObject("postedJobs", user.getJobs());
		modelAndView.addObject("jobs", jobService.findAll());
		modelAndView.addObject("role", util.getRole());
		Set<JobApplication> applications= user.getApplications();
		List<JobInfo> appliedJobList = new ArrayList<JobInfo>();
		for ( JobApplication application : applications ){
			Job job= application.getJob();
			JobInfo jobInfo= new JobInfo();
			jobInfo.setId(application.getId());
			jobInfo.setTitle(job.getTitle());
			jobInfo.setStatus(application.getStatus());
			jobInfo.setDescription(job.getDescription());
			jobInfo.setLocation(job.getLocation());
			appliedJobList.add(jobInfo);
		}
		
		modelAndView.addObject("appliedjobs", appliedJobList);
		modelAndView.setViewName("/SignedInManager");
		
		logger.info("User entred Home page ");
		logger.info("START - Home page ");
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/appliedjob")
	   public ModelAndView appliedJob() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		     ModelAndView mv= new ModelAndView("appliedJob");
		         Job job = new Job();
		
		           mv.addObject("jobs", jobService.findAll());
		              Set<JobApplication> applications= user.getApplications();
		                mv.addObject("appliedjobs",user.getApplications());
		                mv.addObject("role", util.getRole());
		                   return mv;
		
	}	
	
	
	/*  public String getRole () {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
		     String role= null;
	        for ( Role r:user.getRoles()) {
	            r.getRole();
	            if (r.getRole()!=null) return r.getRole();
	        }
	        return role;
	    }*/
	
}

	