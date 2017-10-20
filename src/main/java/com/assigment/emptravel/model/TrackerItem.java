package com.assigment.emptravel.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Entity
@Table(name = "trackerItem1")
public class TrackerItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "track_item_id")
	private int id;
	
	@Column(name="start_date")
    private Date startDate;

	
	@Column(name = "title")
	@NotEmpty(message = "*Please provide  a title")
	private String title;
	
	@Column(name = "description")
	@NotEmpty(message = "*Please provide a title")
	String description;

	String Status;
	
	@ManyToOne
	@JoinColumn(name="track_id", nullable=false)
	Tracker tracker;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	User user;
	
	@OneToMany(mappedBy="item")
	Set<Attachment> attachments;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	

	public Tracker getTracker() {
		return tracker;
	}

	public void setTracker(Tracker tracker) {
		this.tracker = tracker;
	}

	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getStartDate() {
		return startDate;
	}
	
	
}
