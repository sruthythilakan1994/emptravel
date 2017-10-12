package com.assigment.emptravel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.JobSkill;
import com.assigment.emptravel.model.Skill;
import com.assigment.emptravel.model.SkillSet;
import com.assigment.emptravel.model.User;
import com.assigment.emptravel.repository.SkillRepository;
import com.assigment.emptravel.service.SkillService;

@Controller
public class JobSkillController {
	@Autowired
	SkillService skillService;
	@Autowired
	SkillRepository skillRepository;

	
	@RequestMapping(value={ "/jobskill"}, method = RequestMethod.GET)
	public ModelAndView jobskill(){
		//ModelAndView modelAndView = new ModelAndView();
		ModelAndView mv= new ModelAndView("jobSkill");
	   JobSkill skill = new JobSkill();
		mv.addObject("jobSkill", skill);
        mv.addObject("skills", skillService.findAll());
      
		return mv;
	
	}

	
	/*@RequestMapping(value={ "/jobskill"}, method = RequestMethod.GET)
	public ModelAndView jobskill1(){
		ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("jobskill");
		  JobSkill skill = new JobSkill();
		modelAndView.addObject("jobSkill", skill);
		modelAndView.addObject("skills", skillService.findAll());*/
		
	
	
	
}
