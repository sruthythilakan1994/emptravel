package com.assigment.emptravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assigment.emptravel.model.ExpenseClaim;
import com.assigment.emptravel.model.Tracker;



@Repository("expenseClaimRepository")
public interface ExpenseClaimRepository extends JpaRepository<ExpenseClaim, Integer>{
	
}
