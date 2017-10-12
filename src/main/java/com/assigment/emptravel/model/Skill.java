package com.assigment.emptravel.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Skill {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="skill_id")
	int id;
	
	@NotEmpty(message = "*Please provide a name")
	String name;
	
	@OneToMany(mappedBy="skill")
	Set<SkillSet> skills ;
	
	@OneToMany(mappedBy="skill")
	Set<JobSkill> jobSkills;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SkillSet> getSkills() {
		return skills;
	}

	public void setSkills(Set<SkillSet> skills) {
		this.skills = skills;
	}

	public Set<JobSkill> getJobSkills() {
		return jobSkills;
	}

	public void setJobSkills(Set<JobSkill> jobSkills) {
		this.jobSkills = jobSkills;
	}
	
	

}
