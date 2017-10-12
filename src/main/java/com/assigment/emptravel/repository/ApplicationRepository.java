package com.assigment.emptravel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assigment.emptravel.model.JobApplication;
import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.User;

@Repository("applicationRepository")
public interface ApplicationRepository extends JpaRepository<JobApplication, Integer>{
	List<JobApplication> findByUser(User user);
	List<JobApplication> findByJob(Job job);	
	List<JobApplication> findByStatus(String status);
	JobApplication findApplicationById(int id);
}