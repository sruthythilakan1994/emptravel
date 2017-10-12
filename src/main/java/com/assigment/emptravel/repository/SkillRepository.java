package com.assigment.emptravel.repository;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.assigment.emptravel.model.Skill;

@Repository("skillRepository")
public interface SkillRepository extends JpaRepository<Skill ,Integer>{
	

}
