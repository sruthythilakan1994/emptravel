package com.assigment.emptravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assigment.emptravel.model.JobSkill;
import com.assigment.emptravel.model.SkillSet;

@Repository("jobSkillRepository")
public interface JobSkillRepository extends JpaRepository<JobSkill ,Integer>{

}
