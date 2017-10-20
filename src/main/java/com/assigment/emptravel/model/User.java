package com.assigment.emptravel.model;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private long id;
	
	@Column(name = "emp_id")
	@Length(min = 3,message = "*Please provide a valid Employee Id")
	@NotEmpty(message = "*Please provide an Employee Id")
	private String empId;
	
	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	
	@Column(name = "password")
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	@Transient
	private String password;
	
	
	
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	@Transient
	private String passwordConfirmed;
	
	@Column(name = "name")
	@NotEmpty(message = "*Please provide your name")
	private String name;
	
	@Column(name = "last_name")
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;
	

	@Column(name = "designation")
	//@NotEmpty(message = "*Please provide your designation")
	private String designation;

	@Column(name = "qualification")
	//@NotEmpty(message = "*Please provide your qualification")
	private String qualification;
	
	@Column(name = "address")
	//@NotEmpty(message = "*Please provide your address")
	private String address;
	
	/*@Column(name = "Phone_number")
	@NotEmpty(message = "*Please provide your phone number")
	private int phoneNumber;*/
	

	@Column(name = "account_number")
	//@NotEmpty(message = "*Please provide your last name")
	private String accountNumber;
	

	@Transient
	private String assignedRole;
	

	@Column(name = "exp_years")
	private int expYears;
	
	
	@Column(name = "active")
	private int active;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	
	//@ManyToMany(cascade = CascadeType.ALL)
	//@JoinTable(name = "user_job1", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "job_id"))
	//Set<Job> jobs;
	
	@OneToMany (mappedBy="user")
	Set<JobApplication> applications;
	
	
	@OneToMany(mappedBy="user")
	Set<SkillSet> skills ;

	
	@OneToMany(mappedBy="user")
	Set<Job> jobs;
	
	@ManyToMany(mappedBy="users")
	Set<Tracker> trackers;
	
	@ManyToMany(mappedBy= "users")
	Set<ExpenseTracker> expenseTracker;
	
	
	@OneToMany(mappedBy="user")
	Set<TrackerItem> trackerItems;
	
	
	
	public Set<ExpenseTracker> getExpenseTracker() {
		return expenseTracker;
	}

	public void setExpenseTracker(Set<ExpenseTracker> expenseTracker) {
		this.expenseTracker = expenseTracker;
	}

	public Set<Tracker> getTrackers() {
		return trackers;
	}

	public void setTrackers(Set<Tracker> trackers) {
		this.trackers = trackers;
	}

	public Set<SkillSet> getSkills() {
		return skills;
	}

	public void setSkills(Set<SkillSet> skills) {
		this.skills = skills;
	}

	public Set<JobApplication> getApplications() {
		return applications;
	}

	public void setApplications(Set<JobApplication> applications) {
		this.applications = applications;
	}

	public long getId() {
		return id;
	}

	

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
    
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	/*

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
*/
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	

	public int getExpYears() {
		return expYears;
	}

	public void setExpYears(int expYears) {
		this.expYears = expYears;
	}

	public String getPasswordConfirmed() {
		return passwordConfirmed;
	}

	public void setPasswordConfirmed(String passwordConfirmed) {
		this.passwordConfirmed = passwordConfirmed;
	}

	public String getAssignedRole() {
		return assignedRole;
	}

	public void setAssignedRole(String assignedRole) {
		this.assignedRole = assignedRole;
	}

	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	public Set<TrackerItem> getTrackerItems() {
		return trackerItems;
	}

	public void setTrackerItems(Set<TrackerItem> trackerItems) {
		this.trackerItems = trackerItems;
	}
	
	
	
	

}
