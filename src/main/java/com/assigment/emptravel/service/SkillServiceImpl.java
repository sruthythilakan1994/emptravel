package com.assigment.emptravel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.JobSkill;
import com.assigment.emptravel.model.Skill;
import com.assigment.emptravel.model.SkillSet;
import com.assigment.emptravel.model.User;
import com.assigment.emptravel.repository.JobRepository;
import com.assigment.emptravel.repository.JobSkillRepository;
import com.assigment.emptravel.repository.SkillRepository;
import com.assigment.emptravel.repository.SkillSetRepository;
import com.assigment.emptravel.repository.UserRepository;
@Service("skillService")
public class SkillServiceImpl implements  SkillService{
	
	@Autowired
	SkillRepository skillRepository;
	
	@Autowired
	SkillSetRepository skillSetRepository;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	JobSkillRepository jobskillRepository;
	@Autowired
	JobRepository jobRepository;

	
	@Override
	
	public void saveSkill(Skill skill) {
		// TODO Auto-generated method stub
		skillRepository.save(skill);
		
	}

	@Override
	public List<Skill> findAll() {
		
	return	skillRepository.findAll();
		
	}

	@Override
	public Skill findSkillById(int id) {
		
		return skillRepository.getOne(id);
	}
	@Override
	public void saveJobSkill(JobSkill jobskill) {
		
	 jobskillRepository.save(jobskill);
	}

	@Override
	public List<JobSkill> findAllSkill() {
		// TODO Auto-generated method stub
		return jobskillRepository.findAll();
	}
 
  
	
	public void skillSetCreate(SkillSet skillentry, User user,Skill skill) {
		
		skillentry.setSkill(skill);
		skillentry.setUser(user);
		skillSetRepository.save(skillentry);
		
		user.getSkills().add(skillentry);
		userRepository.save(user);
		
		
		skill.getSkills().add(skillentry);
		skillRepository.save(skill);
		
		
	}

	
		
		
	

	@Override
	public void addSkill(JobSkill jobSkill, Job job, Skill skill) {
		
		jobSkill.setJob(job);
		jobSkill.setSkill(skill);
		jobskillRepository.save(jobSkill);
		
		job.getSkill().add(jobSkill);
		jobRepository.save(job);
		
		skill.getJobSkills().add(jobSkill);
		skillRepository.save(skill);
		
		  
		
	}

	
}
