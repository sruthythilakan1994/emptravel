package com.assigment.emptravel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assigment.emptravel.model.TrackerItem;
import com.assigment.emptravel.repository.TrackerItemRepository;

@Service("trackerItemService")
public class TrackerItemServiceImpl implements  TrackerItemService{
@Autowired
TrackerItemRepository trackerItemRepository;
	@Override
	public TrackerItem findById(int id) {
		// TODO Auto-generated method stub
		return trackerItemRepository.getOne(id);
	}
	@Override
	public void saveItem(TrackerItem item) {
		trackerItemRepository.save(item);
		
	}
	
	

}
