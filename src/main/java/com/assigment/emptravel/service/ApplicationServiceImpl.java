package com.assigment.emptravel.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assigment.emptravel.model.JobApplication;
import com.assigment.emptravel.model.ApplicationInfo;
import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.Role;
import com.assigment.emptravel.model.Tracker;
import com.assigment.emptravel.model.User;
import com.assigment.emptravel.repository.ApplicationRepository;
import com.assigment.emptravel.repository.JobRepository;
import com.assigment.emptravel.repository.RoleRepository;
import com.assigment.emptravel.repository.TrackerRepository;
import com.assigment.emptravel.repository.UserRepository;

@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService{
	
		@Autowired
		private ApplicationRepository applicationRepository;

		@Override
		public JobApplication findApplicationById(int id) {
			return applicationRepository.getOne(id);
		}
		
		@Autowired
		private JobRepository jobRepository;

		
		@Autowired
		private UserRepository userRepository;


		@Autowired
		private TrackerRepository trackerRepository;


		@Override
		public void saveApplication(JobApplication application) {
		
			
			if (application.getStatus().equals("APPROVED")) {
				
				Job job = application.getJob();
				User employee= application.getUser();
				User manager= job.getUser();
				Set<User> users = new HashSet();
				users.add(employee);
				users.add(manager);
				Tracker tracker= new Tracker();
				tracker.setJob(job);
				tracker.setJobApplication(application);
				tracker.setUsers(users);
				
				trackerRepository.save(tracker) ;
				
				employee.getTrackers().add(tracker);
				userRepository.save(employee);
				
				manager.getTrackers().add(tracker);
				userRepository.save(manager);
				
				
				job.getTrackers().add(tracker);
				jobRepository.save(job);
				
				application.setTracker(tracker);;
				
			}
			
			applicationRepository.save(application);
			
		}

		@Override
		public List<JobApplication> findAll() {
			return applicationRepository.findAll();
		}
	
}