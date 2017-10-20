package com.assigment.emptravel.service;

import com.assigment.emptravel.model.Tracker;
import com.assigment.emptravel.model.TrackerItem;

public interface TrackerItemService {
	 public TrackerItem findById(int id) ;
	 public void saveItem(TrackerItem  item);

}
