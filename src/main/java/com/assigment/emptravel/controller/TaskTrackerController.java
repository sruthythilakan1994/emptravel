package com.assigment.emptravel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.assigment.emptravel.model.Skill;
import com.assigment.emptravel.model.Tracker;
import com.assigment.emptravel.model.TrackerItem;
import com.assigment.emptravel.model.User;
import com.assigment.emptravel.service.TrackerService;
import com.assigment.emptravel.service.UserService;

@Controller
public class TaskTrackerController {
	
	@Autowired 
	UserService userService;
	
	@Autowired
	TrackerService trackerService;
	
	@RequestMapping(value = "/tasktracker")
	public ModelAndView viewtracker() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		
		
		ModelAndView modelAndView = new ModelAndView();
		Tracker task = new Tracker();
		modelAndView.addObject("trackers",user.getTrackers() );
		modelAndView.setViewName("tracker");
		
		return modelAndView ;

}
	
	

	@RequestMapping(value = "/tasktracker/{id}")
	public ModelAndView viewtracker(@PathVariable int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		ModelAndView modelAndView = new ModelAndView();
		TrackerItem item= new TrackerItem();
		item.setTracker(trackerService.findById(id));
		modelAndView.addObject("item", item);
		modelAndView.addObject("actionItems",trackerService.findById(id).getActionItems() );
		modelAndView.setViewName("item");
		
		return modelAndView ;

}
	
}