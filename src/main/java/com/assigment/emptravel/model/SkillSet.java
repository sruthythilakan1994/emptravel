package com.assigment.emptravel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "skillset")
public class SkillSet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "set_id")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	User user;
	
	@ManyToOne
	@JoinColumn(name="skill_id", nullable=false)
	Skill skill;
	
	@Column(name = "technical_skills")
	@NotEmpty(message = "*Please provide your skills ")
	private String technicalSkills;
	
	@Column(name = "skill_experience")
	//@NotEmpty(message = "*Please provide your skill experience")
	private int skillExperience;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	

	public String getTechnicalSkills() {
		return technicalSkills;
	}

	public void setTechnicalSkills(String technicalSkills) {
		this.technicalSkills = technicalSkills;
	}

	public int getSkillExperience() {
		return skillExperience;
	}

	public void setSkillExperience(int skillExperience) {
		this.skillExperience = skillExperience;
	}
	
	
}
