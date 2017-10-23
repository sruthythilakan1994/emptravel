package com.assigment.emptravel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assigment.emptravel.model.ExpenseClaim;
import com.assigment.emptravel.repository.ExpenseClaimRepository;

@Service("expenseClaimService")
public class ExpenseClaimServiceImpl implements ExpenseClaimService {

	@Autowired
	ExpenseClaimRepository expenseClaimRepository;

	@Override
	public ExpenseClaim findById(int id) {
		// TODO Auto-generated method stub
		return expenseClaimRepository.findOne(id);
	}

	@Override
	public void approve(ExpenseClaim inExpenseClaim) {
		
		ExpenseClaim expenseClaim = findById(inExpenseClaim.getId());
		expenseClaim.setApproverMessage(inExpenseClaim.getApproverMessage());
		expenseClaim.setStatus(inExpenseClaim.getStatus());
		expenseClaimRepository.save(expenseClaim);
		
	}
	

}
