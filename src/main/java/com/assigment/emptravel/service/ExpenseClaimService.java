package com.assigment.emptravel.service;

import java.util.List;

import com.assigment.emptravel.model.ExpenseClaim;
import com.assigment.emptravel.model.JobApplication;

public interface ExpenseClaimService {
	public ExpenseClaim findById(int id);
	
	public void approve(ExpenseClaim expenseClaim);
	
	
	
}

