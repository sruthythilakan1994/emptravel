package com.assigment.emptravel.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.assigment.emptravel.model.ExpenseClaim;
import com.assigment.emptravel.model.ExpenseTracker;
import com.assigment.emptravel.model.Job;
import com.assigment.emptravel.model.JobApplication;
import com.assigment.emptravel.model.Tracker;
import com.assigment.emptravel.model.User;
import com.assigment.emptravel.service.ExpenseClaimService;
import com.assigment.emptravel.service.ExpenseTrackerService;
import com.assigment.emptravel.service.JobService;
import com.assigment.emptravel.service.UserService;
import com.assigment.emptravel.util.Util;

@Controller
public class ExpenseTrackerController {
	
	@Autowired
	UserService userService;
	@Autowired
	ExpenseTrackerService expenseTrackerService;

	@Autowired
	JobService jobService;
	
	@Autowired
	Util util;
	
	
	@Autowired
	private ExpenseClaimService expenseClaimService;
	
	@RequestMapping(value = "/Expense")
	public ModelAndView viewexpense() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		ModelAndView modelAndView = new ModelAndView();
		//ExpenseTracker  expense = new  ExpenseTracker();
		//modelAndView.addObject("trackers",user.getTrackers() );
		modelAndView.setViewName("expense");
		modelAndView.addObject("role", util.getRole());
		return modelAndView ;

	}
	
	@RequestMapping(value = "/createExpenseTracker" )
	public ModelAndView createExpense() {
		ModelAndView modelAndView = new ModelAndView();
			
		      ExpenseTracker expenseTracker = new ExpenseTracker();
						 List<User> users= userService.findAll();
						 List<Job> jobs= jobService.findAll();
						 modelAndView.addObject("users", users);
						 modelAndView.addObject("jobs", jobs);
						 modelAndView.addObject("expenseTracker", expenseTracker);
						// modelAndView.addObject("successMessage", "expense tracker has been created successfully.");
						 modelAndView.setViewName("/createExpenseTracker");
						 modelAndView.addObject("role", util.getRole());
					return modelAndView;	
	}
	
	
	@RequestMapping(value = "/expense/create",  method = RequestMethod.POST)
	public ModelAndView createExpense(  @Valid ExpenseTracker expenseTracker,  BindingResult bindingResult) {
		
		
		
		ModelAndView modelAndView = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User manager = userService.findUserByEmail(auth.getName());
		User employee = userService.findUserById( expenseTracker.getEmployee().getId());
		Job job= jobService.findById( expenseTracker.getJob().getId() );
		
		if (bindingResult.hasErrors()) {
			
			 List<User> users= userService.findAll();
			 List<Job> jobs= jobService.findAll();
			 modelAndView.addObject("users", users);
			 modelAndView.addObject("jobs", jobs);
			 modelAndView.addObject("expenseTracker",expenseTracker);	
			
		}
		else {
			 expenseTrackerService.saveExpenseTracker(expenseTracker, manager, employee, job);
			 List<User> users= userService.findAll();
			 List<Job> jobs= jobService.findAll();
			 modelAndView.addObject("users", users);
			 modelAndView.addObject("jobs", jobs);
			 modelAndView.addObject("expenseTracker",expenseTracker);
			 modelAndView.addObject("successMessage", "expense tracker has been created successfully.");
		}
		 modelAndView.setViewName("/createExpenseTracker");
		 modelAndView.addObject("role", util.getRole());
			
		return modelAndView;
		
	}
	
	
	
	
	@RequestMapping(value = "/expensetracker/claim/{id}" )
	public ModelAndView createExpense( @PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView() ;
		ExpenseTracker expenseTracker= expenseTrackerService.findById(id);
		
		ExpenseClaim expenseClaim= new ExpenseClaim();
		expenseClaim.setExpenseTracker(expenseTracker);
		
		modelAndView.addObject("expenseClaim", expenseClaim);
		modelAndView.setViewName("/expenseClaim");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;	
	}
	

	/*@RequestMapping(value = "/expense/claim")
	public ModelAndView expenseClaim() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		ModelAndView modelAndView = new ModelAndView();
		ExpenseTracker  expenseClaim = new  ExpenseTracker();
		modelAndView.addObject("expenseClaim", expenseClaim);
		//modelAndView.addObject("trackers",user.getTrackers() );
		modelAndView.setViewName("expenseClaim");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}*/
	
	
	
	@RequestMapping(value = "/expense/claim",  method = RequestMethod.POST)
	public ModelAndView claimExpense(  @Valid  ExpenseClaim expenseClaim,  BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		byte[] bytes =null;
		
		String fullFilePath=null;
		if (expenseClaim.getFile().getOriginalFilename()!=null && expenseClaim.getFile().getOriginalFilename().length()>3) {
			 fullFilePath= "/home/sruthy/emptravel_upload_files/" +new Date().getTime()+ "_" + expenseClaim.getFile().getOriginalFilename();
		}
      
		ExpenseTracker expenseTracker =  expenseTrackerService.findById(expenseClaim.getExpenseTracker().getId());
		
			DateTime fromDate = new  DateTime( expenseClaim.getFromDate());
			DateTime toDate = new  DateTime( expenseClaim.getToDate());
			
			
			DateTime startDate = new  DateTime( expenseTracker.getStartDate());
			DateTime endDate = new  DateTime( expenseTracker.getEndDate());
			
			if (fromDate.isAfter(endDate)  || fromDate.isBefore(startDate)) {
				bindingResult
				.rejectValue("fromDate", "error.user",
						"fromDate is invalid");
				
			}
			
			
			if (toDate.isAfter(endDate)  || toDate.isBefore(startDate)) {
				bindingResult
				.rejectValue("toDate", "error.user",
						"To date is not in beteen tracker start and end date");
				
			}
			if (!toDate.isAfter(fromDate) ) {
				bindingResult
				.rejectValue("toDate", "error.user",
						"To date should greater than from date");
				
			}
			//fromDate 
			//toDate 
			
		
		
		
		if (bindingResult.hasErrors()) {
			
			
			expenseClaim.setExpenseTracker(expenseTracker);
			
			modelAndView.setViewName("expenseClaim");
		
			modelAndView.addObject("successMessage", "Could not posted successfully.");
				            
			modelAndView.addObject("expenseClaim", expenseClaim);
			modelAndView.setViewName("/expenseClaim");
			
			
			
		} 
		else {
			//ExpenseTracker expenseTracker =  expenseTrackerService.findById(expenseClaim.getExpenseTracker().getId());
			
			expenseClaim.setAttachedfile(fullFilePath);
			expenseTrackerService.claimExpense(expenseTracker, expenseClaim);
			
			if (expenseClaim.getFile().getOriginalFilename()!=null && expenseClaim.getFile().getOriginalFilename().length()>3) {
			try {
				  bytes = expenseClaim.getFile().getBytes();
				  Path path = Paths.get(fullFilePath);
		          Files.write(path, bytes);
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
			
			modelAndView.addObject("expenseClaim", expenseClaim);
			modelAndView.setViewName("/expenseClaim");
				          
			modelAndView.addObject("successMessage", "expense cliam posted successfully.");
		}
		
		modelAndView.addObject("role", util.getRole());
		return modelAndView;

	}
	
	
	@RequestMapping(value = "/expense/claim/{id}" )
	public ModelAndView viewExpenseClaim( @PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView() ;
		ExpenseTracker expenseTracker= expenseTrackerService.findById(id);
		
		modelAndView.addObject("expenseTracker",expenseTracker);
		modelAndView.setViewName("/claim");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;	
	}
	
	
	
	@RequestMapping(value = "/claim/approval/{id}" )
	public ModelAndView approvalExpenseClaim( @PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView() ;
		ExpenseClaim expenseClaim= expenseClaimService.findById(id);
		
		modelAndView.addObject("expenseClaim",expenseClaim);
		modelAndView.setViewName("/claimapproval");
		modelAndView.addObject("role", util.getRole());
		return modelAndView;	
	}
	
	
	
	
	
	@RequestMapping(value = "expense/approve/submit",  method = RequestMethod.POST)
	public ModelAndView submitExpenseClaimApproval(  @Valid  ExpenseClaim expenseClaim,  BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			ExpenseTracker expenseTracker =  expenseTrackerService.findById(expenseClaim.getExpenseTracker().getId());
			expenseTrackerService.claimExpense(expenseTracker, expenseClaim);
			modelAndView.addObject("expenseClaim", expenseClaim);
			modelAndView.setViewName("/claimapproval");
		} 
		else {
			expenseClaimService.approve(expenseClaim);
			modelAndView.addObject("expenseClaim",  expenseClaimService.findById(expenseClaim.getId()));
			modelAndView.setViewName("/claimapproval");
				          
			modelAndView.addObject("successMessage", "expense cliam posted successfully.");
		}
		
		modelAndView.addObject("role", util.getRole());
		return modelAndView;

	}
	
	
	
	

	    @RequestMapping("/claim/attachmentdownload/{id}")
	    public void downloadPDFResource( HttpServletRequest request,
	                                     HttpServletResponse response,
	                                     @PathVariable("id") int id)
	    {
	        
	    	ExpenseClaim claim= expenseClaimService.findById(id);
	    	//If user is not authorized - he should be thrown out from here itself
	         
	        //Authorized user will download the file
	        String filePath = claim.getAttachedfile();
	       
	        Path file = Paths.get(filePath);
	        
	        
	        String fileName=claim.getAttachedfile().replaceAll("/home/sruthy/emptravel_upload_files/", "");
	        
	        if (Files.exists(file))
	        {
	            response.setContentType("application/octet-stream");
	            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
	            try
	            {
	                Files.copy(file, response.getOutputStream());
	                response.getOutputStream().flush();
	            }
	            catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	
	
	    @RequestMapping(value = "/expensetracker")
		public ModelAndView viewtracker() {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			
			ModelAndView modelAndView = new ModelAndView();
		

			List<ExpenseTracker> trackerList= new ArrayList<>(user.getExpenseTracker());
			Collections.sort(trackerList, new Comparator<ExpenseTracker>() {  
			    @Override  
			    public int compare(ExpenseTracker p1, ExpenseTracker p2) {  
			   	 return new CompareToBuilder().append(p1.getId(),p2.getId()).toComparison();  
					    
			    }  
			}); 
			
			
			modelAndView.addObject("expenseTrackers",trackerList );
			modelAndView.setViewName("expenseTracker");
			modelAndView.addObject("role", util.getRole());
			return modelAndView ;

	}
	
}
