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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Job {

	public Set<ExpenseTracker> getExpenseTracker() {
		return expenseTracker;
	}

	public void setExpenseTracker(Set<ExpenseTracker> expenseTracker) {
		this.expenseTracker = expenseTracker;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="job_id")
	int id;
	
	@NotEmpty(message = "*Please provide a title")
	String title;
	
	
	@NotEmpty(message = "*Please provide a description")
	String description;
	
	@NotEmpty(message = "*Please provide a location")
	String location;
	
	@NotNull(message = "*Please provide experience")
	int minExp;
	
	@NotNull(message = "*Please provide experience")
	int maxExp;
	
	@OneToMany(mappedBy="job")
	Set<JobApplication> applications;
	
	@OneToMany(mappedBy="job")
	Set<JobSkill> skill;
	
	@OneToMany(mappedBy="job")
	Set<Tracker> trackers;
	
	
	@NotEmpty(message = "*Please provide a title")
	String designation;
	
	int duration;
	
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	User user;
	
	String status;
	
	@OneToMany (mappedBy="job")
	Set<ExpenseTracker> expenseTracker;
	
		
	/* @Column(name="start_date")
	 private Date postedDate;
	   

	 @DateTimeFormat(pattern = "dd/MM/yyyy")
	 @NotNull (message = "*Please provide last date to apply")
	 @Column(name="end_date")
	 private Date lastDate ;
	*/
	
	
	
	public Set<JobApplication> getApplications() {
		return applications;
	}

	public void setApplications(Set<JobApplication> applications) {
		this.applications = applications;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/*
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

*/
	

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMinExp() {
		return minExp;
	}

	public void setMinExp(int minExp) {
		this.minExp = minExp;
	}

	public int getMaxExp() {
		return maxExp;
	}

	public void setMaxExp(int maxExp) {
		this.maxExp = maxExp;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<JobSkill> getSkill() {
		return skill;
	}

	public void setSkill(Set<JobSkill> skill) {
		this.skill = skill;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Tracker> getTrackers() {
		return trackers;
	}

	public void setTrackers(Set<Tracker> trackers) {
		this.trackers = trackers;
	}

	
	
	
}
