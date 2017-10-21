package com.assigment.emptravel.model;

import java.util.Date;

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
	
	Date fromDate;
	
	Date toDate;
	
	double dailyAllawance;
	
	double foodAllawance;
	
	double cabAllawance;
	
	String status;
	
	String message;
	
	String approverMessage;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getApproverMessage() {
		return approverMessage;
	}

	public void setApproverMessage(String approverMessage) {
		this.approverMessage = approverMessage;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public double getDailyAllawance() {
		return dailyAllawance;
	}

	public void setDailyAllawance(double dailyAllawance) {
		this.dailyAllawance = dailyAllawance;
	}

	public double getFoodAllawance() {
		return foodAllawance;
	}

	public void setFoodAllawance(double foodAllawance) {
		this.foodAllawance = foodAllawance;
	}

	public double getCabAllawance() {
		return cabAllawance;
	}

	public void setCabAllawance(double cabAllawance) {
		this.cabAllawance = cabAllawance;
	}

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
