package com.assigment.emptravel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = " expenseclaim")
public class ExpenseClaim {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "expense_claim_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="expense_tracker_id", nullable=false)
	ExpenseTracker  expenseTracker;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ExpenseTracker getExpenseTracker() {
		return expenseTracker;
	}

	public void setExpenseTracker(ExpenseTracker expenseTracker) {
		this.expenseTracker = expenseTracker;
	}
	
	
	
}
