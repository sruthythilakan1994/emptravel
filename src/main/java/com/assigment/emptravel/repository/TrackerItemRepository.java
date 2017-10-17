package com.assigment.emptravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assigment.emptravel.model.SkillSet;
import com.assigment.emptravel.model.TrackerItem;

@Repository("trackerItemRepository")
public interface TrackerItemRepository extends JpaRepository<TrackerItem ,Integer>{

}
