package com.assigment.emptravel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assigment.emptravel.model.ExpenseTracker;
import com.assigment.emptravel.repository.ExpenseTrackerRepository;

@Service("expenseTrackerService")
public class ExpenseTrackerServiceImpl  implements ExpenseTrackerService{
 @Autowired
 ExpenseTrackerRepository expenseTrackerRepository;
	@Override
	public void saveExpenseTracker(ExpenseTracker expensetracker) {
		expenseTrackerRepository.save(expensetracker);
		
	}

}
