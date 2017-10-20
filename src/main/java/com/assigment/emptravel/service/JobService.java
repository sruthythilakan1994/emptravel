package com.assigment.emptravel.service;
import java.util.Set;

import java.util.List;

import com.assigment.emptravel.model.JobApplication;
import com.assigment.emptravel.model.ApplicationInfo;
import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.User;


public interface JobService {
	public void saveJob(Job job);
	public void saveJob(Job job, User user);
	public List<Job> findAll();
	public Job findById(int id);
	public boolean applyJob(User user, Job job);
	
	public List<JobApplication> getAllPendingApplication();
	
	public List<JobApplication> getAllApprovedApplication();
	

	public List<JobApplication> getAllRejectedApplication();
	
	public JobApplication getApplication (int id);
	
	
	
	public List<JobApplication> getSortedApplication(Job job);
	
}