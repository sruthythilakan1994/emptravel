package com.assigment.emptravel.service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
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

@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService{
	
		@Autowired
		private ApplicationRepository applicationRepository;

		@Override
		public JobApplication findApplicationById(int id) {
			return applicationRepository.getOne(id);
		}

		@Override
		public void saveApplication(JobApplication application) {
			applicationRepository.save(application);
		}

		@Override
		public List<JobApplication> findAll() {
			return applicationRepository.findAll();
		}
	
}