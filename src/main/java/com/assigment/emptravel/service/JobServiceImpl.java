package com.assigment.emptravel.service;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
	

	@Override
	public void saveJob(Job job) {
		jobRepository.save(job);
	}
	
	@Override
	public List<Job> findAll() {
		 return jobRepository.findAll();
	}


}