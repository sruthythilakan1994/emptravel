package com.assigment.emptravel.service;

import java.util.List;

import com.assigment.emptravel.model.JobApplication;
import com.assigment.emptravel.model.SkillSet;

public interface SkillSetService {
	public void saveSkillSet(SkillSet skillset);
	public List<SkillSet> findAll();
}
