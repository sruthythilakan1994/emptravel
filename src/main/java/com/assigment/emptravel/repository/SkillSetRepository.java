package com.assigment.emptravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assigment.emptravel.model.Skill;
import com.assigment.emptravel.model.SkillSet;

@Repository("skillSetRepository")
public interface SkillSetRepository extends JpaRepository<SkillSet ,Integer> {

	//void save(SkillSet skillset);

}
