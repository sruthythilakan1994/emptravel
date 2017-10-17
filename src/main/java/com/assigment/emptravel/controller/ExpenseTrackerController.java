package com.assigment.emptravel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.assigment.emptravel.model.ExpenseTracker;
import com.assigment.emptravel.model.Tracker;

@Controller
public class ExpenseTrackerController {

	@RequestMapping(value = "/Expense")
	public ModelAndView viewexpense() {
		ModelAndView modelAndView = new ModelAndView();
		ExpenseTracker  expense = new  ExpenseTracker();
		//modelAndView.addObject("trackers",user.getTrackers() );
		modelAndView.setViewName("Expense");
		
		return modelAndView ;

	}
}
