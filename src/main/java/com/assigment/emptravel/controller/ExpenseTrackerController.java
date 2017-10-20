package com.assigment.emptravel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.assigment.emptravel.model.ExpenseTracker;
import com.assigment.emptravel.model.Tracker;
import com.assigment.emptravel.model.User;
import com.assigment.emptravel.service.ExpenseTrackerService;
import com.assigment.emptravel.service.UserService;

@Controller
public class ExpenseTrackerController {
	
	@Autowired
	UserService userService;
	@Autowired
	ExpenseTrackerService expensetrackerservice;

	@RequestMapping(value = "/Expense")
	public ModelAndView viewexpense() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		ModelAndView modelAndView = new ModelAndView();
		//ExpenseTracker  expense = new  ExpenseTracker();
		//modelAndView.addObject("trackers",user.getTrackers() );
		modelAndView.setViewName("Expense");
		
		return modelAndView ;

	}
	@RequestMapping(value = "/expense/create")
	public ModelAndView CreateExpense(@Valid ExpenseTracker expenseTracker, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("expensetracker");
		}
		else {
						expensetrackerservice.saveExpenseTracker(expenseTracker);
						modelAndView.addObject("successMessage", "expense tracker has been created successfully.");
						modelAndView.setViewName("/jobpost");
					}
					return modelAndView;
		
		
		}
	
	
	
}
