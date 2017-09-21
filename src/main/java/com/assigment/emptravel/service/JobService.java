package com.assigment.emptravel.service;
import java.util.List;

import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.User;


public interface JobService {
	public void saveJob(Job job);
	public List<Job> findAll();
	public Job findById(int id);
	public boolean applyJob(User user, Job job);
	
	public List<Job> getAllPendingApplication();
	
	public List<Job> getAllApprovedApplication();
	

	public List<Job> getAllRejectedApplication();
	
	
	
}