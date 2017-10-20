package com.assigment.emptravel.service;

import com.assigment.emptravel.model.Tracker;
import com.assigment.emptravel.model.TrackerItem;
import com.assigment.emptravel.model.User;

public interface TrackerService {
	Tracker findById(int id) ;
	public void addItemsToTracker(TrackerItem actionItem , Tracker tracker ,  User user);
	
}
