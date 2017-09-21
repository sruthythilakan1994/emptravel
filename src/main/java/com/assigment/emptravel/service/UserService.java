package com.assigment.emptravel.service;
import java.util.List;

import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.User;


public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public List<User> findAll();
	//public List<Job> findJobs(int UserId);
}