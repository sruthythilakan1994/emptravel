package com.assigment.emptravel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assigment.emptravel.model.JobApplication;
import com.assigment.emptravel.model.Tracker;
import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.User;

@Repository("trackerRepository")
public interface TrackerRepository extends JpaRepository<Tracker, Integer>{
	
}