package com.assigment.emptravel.service;

import org.springframework.stereotype.Service;

import com.assigment.emptravel.model.ExpenseClaim;
import com.assigment.emptravel.model.ExpenseTracker;
import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.User;

public interface ExpenseTrackerService {
	public void saveExpenseTracker(ExpenseTracker expenseTracker, User manager, User employee, Job job);
    public ExpenseTracker findById(int id);
    public void claimExpense(ExpenseTracker expenseTracker, ExpenseClaim expenseClaim);
}
