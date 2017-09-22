package com.assigment.emptravel.service;
import java.util.List;

import com.assigment.emptravel.model.JobApplication;
import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.User;

public interface ApplicationService {
	public JobApplication findApplicationById(int id);
	public void saveApplication(JobApplication application);
	public List<JobApplication> findAll();
}