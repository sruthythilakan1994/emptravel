package com.assigment.emptravel.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assigment.emptravel.model.ExpenseClaim;
import com.assigment.emptravel.model.ExpenseTracker;
import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.User;
import com.assigment.emptravel.repository.ExpenseClaimRepository;
import com.assigment.emptravel.repository.ExpenseTrackerRepository;
import com.assigment.emptravel.repository.JobRepository;
import com.assigment.emptravel.repository.UserRepository;

@Service("expenseTrackerService")
public class ExpenseTrackerServiceImpl  implements ExpenseTrackerService{

	@Autowired
	ExpenseTrackerRepository expenseTrackerRepository;
 
	@Autowired
	ExpenseClaimRepository expenseClaimRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JobRepository jobRepository;
	
	
	@Override
	public void saveExpenseTracker(ExpenseTracker expenseTracker, User manager, User employee, Job job) {;
	
	Set<User> users= new HashSet<>();
	users.add(manager);
	users.add(employee);
	
	expenseTracker.setJob(job);
	expenseTracker.setUsers(users);
	
	expenseTracker.setEmployee(employee);
	
	expenseTracker.setManager(manager);
	expenseTrackerRepository.save(expenseTracker);
	
	employee.getExpenseTracker().add(expenseTracker);
	userRepository.save(employee);
	
	manager.getExpenseTracker().add(expenseTracker);
	userRepository.save(manager);
	
	job.getExpenseTracker().add(expenseTracker);
	jobRepository.save(job);
	
	}

	
	public ExpenseTracker findById(int id) {
		return  expenseTrackerRepository.findOne(id);
	
	}
	  
	  
	public void claimExpense(ExpenseTracker expenseTracker, ExpenseClaim expenseClaim) {
		
		expenseClaim.setStatus("PENDING");
		expenseClaim.setExpenseTracker(expenseTracker);
		expenseClaimRepository.save(expenseClaim);
		
		expenseTracker.getExpenseClaim().add(expenseClaim);
		expenseTrackerRepository.save(expenseTracker);
			  
	}
	
}
