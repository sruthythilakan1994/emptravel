package com.assigment.emptravel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assigment.emptravel.model.SkillSet;
import com.assigment.emptravel.repository.SkillSetRepository;
@Service("skillSetService")
public class SkillSetServiceImpl implements SkillSetService{
	@Autowired
    SkillSetRepository skillSetRepository;
	@Override
	
	public void saveSkillSet(SkillSet skillset) {
		
		skillSetRepository.save(skillset);
	}
	@Override
	public List<SkillSet> findAll() {
		
		return skillSetRepository.findAll();
	}
	
  
}
