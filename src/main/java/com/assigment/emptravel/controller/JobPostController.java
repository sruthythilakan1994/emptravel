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
import com.assigment.emptravel.model.JobApplication;
import com.assigment.emptravel.model.JobInfo;
import com.assigment.emptravel.model.JobSkill;
import com.assigment.emptravel.model.Skill;
import com.assigment.emptravel.model.User;
import com.assigment.emptravel.repository.JobRepository;
import com.assigment.emptravel.service.JobService;
import com.assigment.emptravel.service.JobSkillService;
import com.assigment.emptravel.service.SkillService;
import com.assigment.emptravel.service.UserService;

@Controller
public class JobPostController {
	
	@Autowired
	JobService jobService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	SkillService skillService;
	@Autowired
	JobSkillService jobSkillService;
	
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
			//modelAndView.addObject("successMessage", "Job has been created successfully.");
			//modelAndView.setViewName("/jobpost");
			
			 modelAndView.setViewName("jobSkill");
			    JobSkill skill = new JobSkill();
			    skill.setJob(job);
			    modelAndView.addObject("jobSkill", skill);
			    modelAndView.addObject("skills", skillService.findAll());
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/jobskill/add", method = RequestMethod.POST)
	public ModelAndView addSkill(@Valid JobSkill jobSkill, BindingResult bindingResult ) {
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("jobSkill");
		} else {
			
			Job job = jobService.findById(jobSkill.getJob().getId());
			Skill skill = skillService.findSkillById(Integer.parseInt(jobSkill.getTechnicalSkills()));
			skillService.addSkill( jobSkill, job , skill);
			
			//skillService.saveSkill(skill);
			modelAndView.addObject("successMessage", "skill has been created successfully.");
			modelAndView.setViewName("jobSkill");
			modelAndView.addObject("JobSkill", jobSkill);
			modelAndView.addObject("skills", skillService.findAll());
			modelAndView.addObject("JobSkills", job.getSkill());
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
			
	
	
	
}
