package com.assigment.emptravel.model;

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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tracker")
public class Tracker {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "track_id")
	private int id;
	
	@OneToMany(mappedBy="tracker")
	Set<TrackerItem> actionItems;
	
	
	@OneToOne
	@JoinColumn(name = "app_id")
	JobApplication jobApplication;
	
	@ManyToOne
	@JoinColumn(name="job_id", nullable=false)
	Job job;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tracker_user", joinColumns = @JoinColumn(name = "track_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users;

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<TrackerItem> getActionItems() {
		return actionItems;
	}

	public void setActionItems(Set<TrackerItem> actionItems) {
		this.actionItems = actionItems;
	}

	public JobApplication getJobApplication() {
		return jobApplication;
	}

	public void setJobApplication(JobApplication jobApplication) {
		this.jobApplication = jobApplication;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}



	
}
