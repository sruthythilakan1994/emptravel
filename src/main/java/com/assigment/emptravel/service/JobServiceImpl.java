package com.assigment.emptravel.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assigment.emptravel.model.JobApplication;
import com.assigment.emptravel.model.ApplicationInfo;
import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.Role;
import com.assigment.emptravel.model.User;
import com.assigment.emptravel.repository.ApplicationRepository;
import com.assigment.emptravel.repository.JobRepository;
import com.assigment.emptravel.repository.RoleRepository;
import com.assigment.emptravel.repository.UserRepository;



@Service("jobService")
public class JobServiceImpl implements JobService{

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Override
	public void saveJob(Job job) {
		job.setStatus("PENDING");
		jobRepository.save(job);
	}
	
	@Override
	public void saveJob(Job job, User user) {
		job.setStatus("PENDING");
		job.setUser(user);
		jobRepository.save(job);
		
		user.getJobs().add(job);
		userRepository.save(user);
	}
	
	
	
	@Override
	public List<Job> findAll() {
		 return jobRepository.findAll();
	}

	@Override
	public Job findById(int id) {
		return jobRepository.findOne(id);
	}
	
	@Transactional
	@Override
	public boolean applyJob(User user, Job job) {
		
		JobApplication application = new JobApplication();
		application.setJob(job);
		application.setUser(user);
		application.setStatus("PENDING");
		
		applicationRepository.save(application);
		
		user.getApplications().add(application);
		userRepository.save(user);
		
		job.getApplications().add(application);
		jobRepository.save(job);
		
		return true;
	}

	public JobApplication getApplication (int id){
		return applicationRepository.findOne(id);
	}
	
	@Override
	public List<JobApplication> getAllPendingApplication() {
		return applicationRepository.findByStatus("PENDING");
	}

	@Override
	public List<JobApplication> getAllApprovedApplication() {
		return applicationRepository.findByStatus("APPROVED");
	}

	@Override
	public List<JobApplication> getAllRejectedApplication() {
		return applicationRepository.findByStatus("REJECTED");
	}
	
	public Set<JobApplication> getSortedApplication(Job job){
		
		//TODO - create list based on raking 
		return job.getApplications();
	}
	
}