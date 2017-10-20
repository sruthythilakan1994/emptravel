package com.assigment.emptravel.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.assigment.emptravel.model.Role;
import com.assigment.emptravel.model.User;
import com.assigment.emptravel.service.UserService;


@Component
public class Util {

	
	@Autowired
	private UserService userService;
	
	  public  String getRole () {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
		     String role= null;
		     if(user!=null && user.getRoles()!= null) {
		    	  for ( Role r:user.getRoles()) {
			            r.getRole();
			            if (r.getRole()!=null) return r.getRole();
			        }
		     }
	      
	        
	        //TODO- remove this
	        if (role==null) role="ADMIN";
	        
	        return role;
	    }
	
}
