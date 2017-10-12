package com.assigment.emptravel.service;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.assigment.emptravel.model.Role;
import com.assigment.emptravel.model.User;
import com.assigment.emptravel.repository.RoleRepository;
import com.assigment.emptravel.repository.UserRepository;



@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	public User findUserByEmpId(String empid) {
		return userRepository.findByEmpId(empid);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}
	
	@Override
	public List<User> findAll() {
		 return userRepository.findAll();
	}

	
	@Override
	public User findUserById(Long id) {
		 return userRepository.findOne(id);
	}

}