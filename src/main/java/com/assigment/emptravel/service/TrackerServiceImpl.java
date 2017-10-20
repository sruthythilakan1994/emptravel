package com.assigment.emptravel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assigment.emptravel.model.Tracker;
import com.assigment.emptravel.model.TrackerItem;
import com.assigment.emptravel.model.User;
import com.assigment.emptravel.repository.TrackerItemRepository;
import com.assigment.emptravel.repository.TrackerRepository;
import com.assigment.emptravel.repository.UserRepository;

@Service
public class TrackerServiceImpl implements TrackerService {

	@Autowired 
	TrackerRepository trackerRepository;
	 @Autowired
	 UserRepository userRepository;
	 
	 @Autowired
	  TrackerItemRepository trackerItemRepository;
	
	@Override
	public Tracker findById(int id) {
		return trackerRepository.findOne(id);
	}

	@Override
	public void addItemsToTracker( TrackerItem actionItem, Tracker tracker, User user) {
	
		
		actionItem.setTracker(tracker);
		actionItem.setUser(user);
		trackerItemRepository.save(actionItem);
		
		user.getTrackerItems().add(actionItem);
		userRepository.save(user);
		
		tracker.getActionItems().add(actionItem);
		trackerRepository.save(tracker);
		
		
	}


	
	
		
		
	

	
	

}
