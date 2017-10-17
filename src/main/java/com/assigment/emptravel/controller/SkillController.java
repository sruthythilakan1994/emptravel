package com.assigment.emptravel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.JobApplication;
import com.assigment.emptravel.model.JobInfo;
import com.assigment.emptravel.model.Skill;
import com.assigment.emptravel.model.SkillSet;
import com.assigment.emptravel.model.User;
import com.assigment.emptravel.repository.SkillRepository;
import com.assigment.emptravel.repository.SkillSetRepository;
import com.assigment.emptravel.service.SkillService;
import com.assigment.emptravel.service.UserService;
import com.assigment.emptravel.util.Util;

@Controller
public class SkillController {
	@Autowired
	UserService userService;
	@Autowired
	SkillService skillService;
	@Autowired
	SkillRepository skillRepository;
	@Autowired
	SkillSetRepository skillSetRepository;

	@Autowired
	Util util;
	
	
	@RequestMapping(value = { "/skill" }, method = RequestMethod.GET)
	public ModelAndView skill() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("skill");
		Skill skill = new Skill();
		modelAndView.addObject("skill", skill);
		modelAndView.addObject("skills", skillService.findAll());
		modelAndView.addObject("role", util.getRole());

		return modelAndView;
	}

	@RequestMapping(value = "/skill/add", method = RequestMethod.POST)
	public ModelAndView viewSkill(@Valid Skill skill, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("skill");
		} else {
			skillService.saveSkill(skill);
			modelAndView.addObject("successMessage", "skill has been created successfully.");
			modelAndView.setViewName("skill");
			modelAndView.addObject("skills", skillService.findAll());
		}
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}

	@RequestMapping(value = { "/updateskillset" }, method = RequestMethod.GET)
	public ModelAndView skillSet() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("skillset");
		SkillSet skill = new SkillSet();
		modelAndView.addObject("skillSet", skill);
		modelAndView.addObject("skills", skillService.findAll());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		modelAndView.addObject("allSkills", user.getSkills());
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}

	@RequestMapping(value = { "/skillset/create" }, method = RequestMethod.POST)
	public ModelAndView skillSetCreate(SkillSet skillentry) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		Skill skill = skillService.findSkillById(Integer.parseInt(skillentry.getTechnicalSkills()));
		skillService.skillSetCreate(skillentry, user, skill);
		// skillentry.setUser(user);
		// skillentry.setSkill(skill);

		// save skillentry using repo
		// SkillSet.setUser(user.getSkills());

		modelAndView.setViewName("skillset");
		// SkillSet skill = new SkillSet();
		modelAndView.addObject("skillSet", skillentry);
		modelAndView.addObject("skills", skillService.findAll());
		modelAndView.addObject("allSkills", user.getSkills());
		// Set<SkillSet> skills = user.getSkills();
		modelAndView.addObject("role", util.getRole());

		return modelAndView;
	}
	/*
	 * private Object getId() { // TODO Auto-generated method stub return null; }
	 */

}
