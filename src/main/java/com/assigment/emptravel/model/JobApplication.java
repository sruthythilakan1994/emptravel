package com.assigment.emptravel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class JobApplication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "app_id")
	int id;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	User user;
	
	@ManyToOne
	@JoinColumn(name="job_id", nullable=false)
	Job job;
	
	@OneToOne (mappedBy="jobApplication")
	Tracker tracker;
	
	String status;
	
	String message;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

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

	public Tracker getTracker() {
		return tracker;
	}

	public void setTracker(Tracker tracker) {
		this.tracker = tracker;
	}
	
	
	
	
	

}
