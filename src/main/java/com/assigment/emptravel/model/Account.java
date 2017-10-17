package com.assigment.emptravel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="account ")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_id")
	private int id;
	
	@OneToOne(mappedBy="account")
	ExpenseTracker expensetracker;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ExpenseTracker getExpensetracker() {
		return expensetracker;
	}

	public void setExpensetracker(ExpenseTracker expensetracker) {
		this.expensetracker = expensetracker;
	}
	
	
	

}



