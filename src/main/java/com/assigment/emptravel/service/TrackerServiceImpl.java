package com.assigment.emptravel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assigment.emptravel.model.Tracker;
import com.assigment.emptravel.repository.TrackerRepository;

@Service
public class TrackerServiceImpl implements TrackerService {

	@Autowired 
	TrackerRepository trackerRepository;
	
	@Override
	public Tracker findById(int id) {
		return trackerRepository.findOne(id);
	}

}
