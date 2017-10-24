package com.assigment.emptravel.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
import com.assigment.emptravel.model.Skill;
import com.assigment.emptravel.model.Tracker;
import com.assigment.emptravel.model.TrackerItem;
import com.assigment.emptravel.model.User;
import com.assigment.emptravel.service.TrackerItemService;
import com.assigment.emptravel.service.TrackerService;
import com.assigment.emptravel.service.UserService;
import com.assigment.emptravel.util.Util;

@Controller
public class TaskTrackerController {
	
	@Autowired 
	UserService userService;
	
	@Autowired
	TrackerService trackerService;
	@Autowired
	TrackerItemService trackerItemService;
	
	@Autowired
	Util util;
	@RequestMapping(value = "/tasktracker")
	public ModelAndView viewtracker() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		
		
		ModelAndView modelAndView = new ModelAndView();
		Tracker task = new Tracker();
		modelAndView.addObject("trackers",user.getTrackers() );
		modelAndView.setViewName("tracker");
		modelAndView.addObject("role", util.getRole());
		return modelAndView ;

}
	
	
	@RequestMapping(value = "/tasktracker/add/{id}")
	public ModelAndView addtask(TrackerItem item, @PathVariable int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		
		TrackerItem item1=new TrackerItem();
		item1.setTitle(item.getTitle());
		item1.setDescription(item.getDescription());
		item1.setStatus(item.getStatus());
		item1.setStartDate(item.getStartDate());
		User user = userService.findUserByEmail(auth.getName());
		
		Tracker tracker= trackerService.findById(id);
		
		trackerService.addItemsToTracker(item1, tracker, user);
		
		ModelAndView modelAndView = new ModelAndView();
		
		List<TrackerItem> xx= trackerService.findById(id).getActionItems() ;
		
		modelAndView.addObject("item", item);
		modelAndView.addObject("actionItems",trackerService.findById(id).getActionItems() );
		modelAndView.setViewName("item");
		
		modelAndView.addObject("role", util.getRole());
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
		modelAndView.addObject("role", util.getRole());
		return modelAndView ;

}
	@RequestMapping(value = "/updatetracker/{id}")
	public ModelAndView edittracker( @PathVariable int id) {
		
		
		ModelAndView modelAndView = new ModelAndView();
	   // TrackerItem item= new TrackerItem();

		TrackerItem  item = trackerItemService.findById(id);
		
		modelAndView.addObject("item", item);
		modelAndView.addObject("actionItems",item.getTracker().getActionItems() );
		
		modelAndView.setViewName("updatetracker");
		
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	
	@RequestMapping(value="/updatetracker/{id}", method = RequestMethod.POST)
	public ModelAndView editTracker(TrackerItem item,@PathVariable int id, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		TrackerItem  itemUpdate =trackerItemService.findById(id);
		itemUpdate.setDescription(item.getDescription());
		itemUpdate.setTitle(item.getTitle());
		itemUpdate.setStatus(item.getStatus());
		itemUpdate.setStartDate(new Date());
		//itemUpdate.setStartDate(item.getStartDate());
		
		trackerItemService.saveItem(itemUpdate);
		modelAndView.addObject("successMessage", "Updated successfully.");
		modelAndView.addObject("item", itemUpdate);
		//modelAndView.addObject("role", util.getRole());
		modelAndView.addObject("actionItems",itemUpdate.getTracker().getActionItems() );
		modelAndView.addObject("role", util.getRole());
		modelAndView.setViewName("/updatetracker");
		
	
	return modelAndView;
	}
	
}