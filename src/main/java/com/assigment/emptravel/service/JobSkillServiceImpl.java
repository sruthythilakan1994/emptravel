package com.assigment.emptravel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.JobSkill;
import com.assigment.emptravel.model.Skill;
import com.assigment.emptravel.repository.JobSkillRepository;

@Service("jobSkillService")
public class JobSkillServiceImpl implements JobSkillService {
	@Autowired
	JobSkillRepository jobSkillRepository;
	

	@Override
	
	public void  saveJobSkill(JobSkill jobskill) {
		
		jobSkillRepository.save(jobskill);
		
	}

	@Override
	public List<JobSkill> findAll() {
		
		return jobSkillRepository.findAll();
	}

	@Override
	public Skill findSkillById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSkill(JobSkill jobskill, Job job, Skill skill) {
		// TODO Auto-generated method stub
		
	}

}
