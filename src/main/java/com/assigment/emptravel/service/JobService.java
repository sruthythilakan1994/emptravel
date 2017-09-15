package com.assigment.emptravel.service;
import java.util.List;

import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.User;


public interface JobService {
	public void saveJob(Job job);
	public List<Job> findAll();
}