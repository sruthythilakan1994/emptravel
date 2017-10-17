package com.assigment.emptravel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Attachment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "attachment_id")
	private long id;
	
	@Column(name = "path")
	private String path;
	
    @ManyToOne
    @JoinColumn(name="track_item_id", nullable=false)
	TrackerItem item;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public TrackerItem getItem() {
		return item;
	}

	public void setItem(TrackerItem item) {
		this.item = item;
	}
    
    
    
}
