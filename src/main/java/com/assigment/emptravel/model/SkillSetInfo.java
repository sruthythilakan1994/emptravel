package com.assigment.emptravel.model;

public class SkillSetInfo {
	int id;

	UserInfo userInfo;

	SkillInfo skillInfo;
	
	String technicalSkills;
	
	String skillExperience;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public SkillInfo getSkillInfo() {
		return skillInfo;
	}

	public void setSkillInfo(SkillInfo skillInfo) {
		this.skillInfo = skillInfo;
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
	
}
