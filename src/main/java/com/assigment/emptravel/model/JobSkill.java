package com.assigment.emptravel.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Table(name = "jobskill")
public class JobSkill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jobskill_id")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="skill_id", nullable=false)
	Skill skill;
	
	@ManyToOne
	@JoinColumn(name="job_id", nullable=false)
	Job job;
	
	@Column(name = "technical_skills")
	@NotEmpty(message = "*Please provide your skills ")
	private String technicalSkills;
	
	@Column(name = "skill_experience")
	@NotEmpty(message = "*Please provide your skill experience")
	private String skillExperience;
	
	
	


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTechnicalSkills() {
		return technicalSkills;
	}

	public void setTechnicalSkills(String technicalSkills) {
		this.technicalSkills = technicalSkills;
	}

	public String getSkillExperience() {
		return skillExperience;
	}

	public void setSkillExperience(String skillExperience) {
		this.skillExperience = skillExperience;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
	
	
	
}
