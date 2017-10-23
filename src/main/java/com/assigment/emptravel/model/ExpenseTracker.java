package com.assigment.emptravel.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "expensetracker")
public class ExpenseTracker {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "expense_track_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "job_id")
	Job job;
	
	
	@OneToOne
	@JoinColumn(name = "account_id")
	Account  account;
	
	@OneToMany(mappedBy="expenseTracker")
	Set<ExpenseClaim> expenseClaim;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "expense_tracker_user", joinColumns = @JoinColumn(name = "expense_track_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users;
	
	

	@OneToOne
	@JoinColumn(name = "employee_id")
	private User employee;

	@JoinColumn(name = "manager_id")
	@OneToOne
	private User manager;
	
	 @DateTimeFormat(pattern = "dd/MM/yyyy")
	 @NotNull (message = "*Please provide your daily allowancee ")
	 @Column(name="start_date")
	 private Date startDate;
	   

	 @DateTimeFormat(pattern = "dd/MM/yyyy")
	 @NotNull (message = "*Please provide your daily allowancee ")
	 @Column(name="end_date")
	 private Date endDate;
	 
	@Min(value=0)
	@Max(value=10000)
	@Column(name = "daily_allowance")
	@NotNull(message = "*Please provide your daily allowancee ")
	private int dailyAllowance;
	

	@Min(value=0)
	@Max(value=10000)
	@Column(name = "food_expense")
	@NotNull(message = "*Please provide your food expense")
	private int foodExpense;
	
	
	
	@Column(name = "cab_expense")
	@NotNull(message = "*Please provide yourcab expense")
	private int cabExpense;
	
	@Column(name = "country")
	@NotNull(message = "*Please provide your country ")
	private String country;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Set<ExpenseClaim> getExpenseClaim() {
		return expenseClaim;
	}

	public void setExpenseClaim(Set<ExpenseClaim> expenseClaim) {
		this.expenseClaim = expenseClaim;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public int getDailyAllowance() {
		return dailyAllowance;
	}

	public void setDailyAllowance(int dailyAllowance) {
		this.dailyAllowance = dailyAllowance;
	}

	public int getFoodExpense() {
		return foodExpense;
	}

	public void setFoodExpense(int foodExpense) {
		this.foodExpense = foodExpense;
	}

	public int getCabExpense() {
		return cabExpense;
	}

	public void setCabExpense(int cabExpense) {
		this.cabExpense = cabExpense;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
