package com.assigment.emptravel.service;

import java.util.List;

import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.JobSkill;
import com.assigment.emptravel.model.Skill;

public interface JobSkillService {
	public void saveJobSkill(JobSkill jobskill);
	public List<JobSkill> findAll();
	public Skill findSkillById(int id);
	public void addSkill(JobSkill jobskill,Job job ,Skill skill);

}
