package com.assigment.emptravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assigment.emptravel.model.ExpenseTracker;

@Repository("expenseTrackerRepository")
public interface ExpenseTrackerRepository extends  JpaRepository<ExpenseTracker,Integer> {

}
