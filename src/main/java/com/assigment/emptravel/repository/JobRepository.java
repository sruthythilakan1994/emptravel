package com.assigment.emptravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assigment.emptravel.model.Job;

@Repository("jobRepository")
public interface JobRepository extends JpaRepository<Job, Integer>{
	
}