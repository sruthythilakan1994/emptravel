package com.assigment.emptravel.controller;

import java.util.List;

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

import com.assigment.emptravel.model.ExpenseClaim;
import com.assigment.emptravel.model.ExpenseTracker;
import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.Tracker;
import com.assigment.emptravel.model.User;
import com.assigment.emptravel.service.ExpenseTrackerService;
import com.assigment.emptravel.service.JobService;
import com.assigment.emptravel.service.UserService;

@Controller
public class ExpenseTrackerController {
	
	@Autowired
	UserService userService;
	@Autowired
	ExpenseTrackerService expenseTrackerService;

	@Autowired
	JobService jobService;
	@RequestMapping(value = "/Expense")
	public ModelAndView viewexpense() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		ModelAndView modelAndView = new ModelAndView();
		//ExpenseTracker  expense = new  ExpenseTracker();
		//modelAndView.addObject("trackers",user.getTrackers() );
		modelAndView.setViewName("expense");
		
		return modelAndView ;

	}
	@RequestMapping(value = "/expense" )
	public ModelAndView createExpense() {
		ModelAndView modelAndView = new ModelAndView();
			
		      ExpenseTracker expenseTracker = new ExpenseTracker();
						 List<User> users= userService.findAll();
						 List<Job> jobs= jobService.findAll();
						 modelAndView.addObject("users", users);
						 modelAndView.addObject("jobs", jobs);
						 modelAndView.addObject("expenseTracker", expenseTracker);
						// modelAndView.addObject("successMessage", "expense tracker has been created successfully.");
						 modelAndView.setViewName("/expenseTracker");
					
					return modelAndView;	
	}
	
	
	@RequestMapping(value = "/expense/create",  method = RequestMethod.POST)
	public ModelAndView createExpense(   ExpenseTracker expenseTracker) {
		ModelAndView modelAndView = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User manager = userService.findUserByEmail(auth.getName());
		User employee = userService.findUserById( expenseTracker.getEmployee().getId());
		Job job= jobService.findById( expenseTracker.getJob().getId() );
		expenseTrackerService.saveExpenseTracker(expenseTracker, manager, employee, job);
			            
		 List<User> users= userService.findAll();
		 List<Job> jobs= jobService.findAll();
		 modelAndView.addObject("users", users);
		 modelAndView.addObject("jobs", jobs);
		 modelAndView.addObject("expenseTracker",expenseTracker);
			          
						 modelAndView.addObject("successMessage", "expense tracker has been created successfully.");
						 modelAndView.setViewName("/expenseTracker");
					
					return modelAndView;
		
	}
	
	
	
	
	@RequestMapping(value = "/expensetracker/claim/{id}" )
	public ModelAndView createExpense( @PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView() ;
		ExpenseTracker expenseTracker= expenseTrackerService.findById(id);
		
		ExpenseClaim expenseClaim= new ExpenseClaim();
		expenseClaim.setExpenseTracker(expenseTracker);
		
		modelAndView.addObject("expenseClaim", expenseClaim);
		modelAndView.setViewName("/expenseClaim");
		return modelAndView;	
	}
	
	
	@RequestMapping(value = "/expense/claim",  method = RequestMethod.POST)
	public ModelAndView claimExpense(   ExpenseClaim expenseClaim) {
		ModelAndView modelAndView = new ModelAndView();
		ExpenseTracker expenseTracker =  expenseTrackerService.findById(expenseClaim.getExpenseTracker().getId());
		expenseTrackerService.claimExpense(expenseTracker, expenseClaim);
			            
		modelAndView.addObject("expenseClaim", expenseClaim);
		modelAndView.setViewName("/expenseClaim");
			          
		modelAndView.addObject("successMessage", "expense cliam posted successfully.");
		return modelAndView;

	}
	
}
