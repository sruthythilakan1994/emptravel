package com.assigment.emptravel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.JobApplication;
import com.assigment.emptravel.model.User;

@Repository("jobRepository")
public interface JobRepository extends JpaRepository<Job, Integer>{
	List<Job> findByStatus(String status);
	
	
}