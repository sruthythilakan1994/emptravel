package com.assigment.emptravel.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assigment.emptravel.model.JobApplication;
import com.assigment.emptravel.model.JobSkill;
import com.assigment.emptravel.model.ApplicationInfo;
import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.Role;
import com.assigment.emptravel.model.SkillSet;
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
	
	public List<JobApplication> getSortedApplication(Job job){
		
		List<JobApplication> filteredApplications= new ArrayList();
		
		Set<JobSkill>  jobSkills= job.getSkill();
		Set<JobApplication> applications = job.getApplications();
		Map<Integer,Integer> jobSkillsMap= getJobSkillMap( jobSkills);
		
		for (JobApplication application: applications) {
			Set <SkillSet> userSkills= application.getUser().getSkills();
			Map<Integer,Integer>  userSkillsMap= getUserSkillMap( userSkills);
			
			//Checking user has all the skill as per the job skill map
			if (userSkillsMap.keySet().containsAll(jobSkillsMap.keySet())) {
				
				for ( Entry<Integer, Integer> entry : jobSkillsMap.entrySet()) {
					int jobExp = entry.getValue();
					int userExp =userSkillsMap.get(entry.getKey());
					if (userExp>=jobExp) {
						filteredApplications.add(application);
					}
					
				}
			}
			
			//user profile exp
			int jobMinExp= job.getMinExp();
			int jobMaxExp= job.getMaxExp();
			if (application.getUser().getExpYears()< jobMaxExp && application.getUser().getExpYears()> jobMinExp ) {
			}
			else {
				filteredApplications.remove(application);
			}
			
		}
		
		
		
		Collections.sort(filteredApplications, new Comparator<JobApplication>() {  
		    @Override  
		    public int compare(JobApplication p1, JobApplication p2) {  
		    //    return new CompareToBuilder().append(p1.getUser().getExpYears(),p2.getUser().getExpYears()).append(p1.nrOfToppings, p2.nrOfToppings).append(p1.name, p2.name).toComparison();  
		    	 return new CompareToBuilder().append(p1.getUser().getExpYears(),p2.getUser().getExpYears()).toComparison();  
				    
		    }  
		});  
	
		
		
		
		
		
		return filteredApplications;
		//TODO - create list based on raking 
		//return job.getApplications();
	}
	
	
	
	Map<Integer,Integer>  getUserSkillMap(Set <SkillSet> userSkills){
		
		Map<Integer,Integer> userSkillMap= new HashMap<>();
		
		for (SkillSet userSkill: userSkills) {
			userSkillMap.put(userSkill.getSkill().getId(),userSkill.getSkillExperience() );
		}
		return userSkillMap;
	}
	
	
	Map<Integer,Integer>  getJobSkillMap(Set<JobSkill>  jobSkills){
		
		Map<Integer,Integer> jobSkillMap= new HashMap<>();
		
		for (JobSkill jobSkill: jobSkills) {
			jobSkillMap.put(jobSkill.getSkill().getId(),jobSkill.getSkillExperience() );
		}
		return jobSkillMap;
	}
	
	
}