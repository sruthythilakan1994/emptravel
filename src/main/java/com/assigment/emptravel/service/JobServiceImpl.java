package com.assigment.emptravel.service;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.Role;
import com.assigment.emptravel.model.User;
import com.assigment.emptravel.repository.JobRepository;
import com.assigment.emptravel.repository.RoleRepository;
import com.assigment.emptravel.repository.UserRepository;



@Service("jobService")
public class JobServiceImpl implements JobService{

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private UserRepository userRepository;

	
	@Override
	public void saveJob(Job job) {
		job.setStatus("PENDING");
		jobRepository.save(job);
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
		user.getJobs().add(job);
		userRepository.save(user);
		
		job.getUsers().add(user);
		jobRepository.save(job);
		
		return true;
	}

	@Override
	public List<Job> getAllPendingApplication() {
		return jobRepository.findByStatus("PENDING");
	}

	@Override
	public List<Job> getAllApprovedApplication() {
		return jobRepository.findByStatus("APPROVED");
	}

	@Override
	public List<Job> getAllRejectedApplication() {
		return jobRepository.findByStatus("REJECTED");
	}
	
	
}