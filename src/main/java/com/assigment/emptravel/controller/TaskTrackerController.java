package com.assigment.emptravel.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.assigment.emptravel.model.ExpenseClaim;
import com.assigment.emptravel.model.JobApplication;
import com.assigment.emptravel.model.Skill;
import com.assigment.emptravel.model.Tracker;
import com.assigment.emptravel.model.TrackerItem;
import com.assigment.emptravel.model.User;
import com.assigment.emptravel.service.TrackerItemService;
import com.assigment.emptravel.service.TrackerService;
import com.assigment.emptravel.service.UserService;
import com.assigment.emptravel.util.Util;

@Controller
public class TaskTrackerController {
	
	@Autowired 
	UserService userService;
	
	@Autowired
	TrackerService trackerService;
	@Autowired
	TrackerItemService trackerItemService;
	
	@Autowired
	Util util;
	
	@RequestMapping(value = "/tasktracker")
	public ModelAndView viewtracker() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		
		
		ModelAndView modelAndView = new ModelAndView();
		Tracker task = new Tracker();
		modelAndView.addObject("trackers",user.getTrackers() );
		modelAndView.setViewName("tracker");
		modelAndView.addObject("role", util.getRole());
		return modelAndView ;

}
	
	
	@RequestMapping(value = "/tasktracker/add/{id}")
	public ModelAndView addtask(@Valid TrackerItem item,  BindingResult bindingResult, @PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			
			modelAndView.addObject("item", item);
			modelAndView.addObject("actionItems",trackerService.findById(id).getActionItems() );
			modelAndView.setViewName("item");
			
			if (item.getTitle()==null ||item.getTitle().length()<1) {
				modelAndView.addObject("successMessage", "Please provide title");
			}
			if (item.getDescription()==null ||item.getDescription().length()<1) {
				modelAndView.addObject("successMessage", "Please provide description");
			}
			if ((item.getTitle()==null ||item.getTitle().length()<1) && (item.getDescription()==null ||item.getDescription().length()<1)){
				modelAndView.addObject("successMessage", "Please provide title and decription");
			}
			
		}else {
		
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			byte[] bytes =null;
			String fullFilePath=null;
			if (item.getFile().getOriginalFilename()!=null && item.getFile().getOriginalFilename().length() >3) {
			 fullFilePath= "/home/sruthy/emptravel_upload_files/" +new Date().getTime()+ "_" + item.getFile().getOriginalFilename();
			}
	      
			TrackerItem item1=new TrackerItem();
			item1.setTitle(item.getTitle());
			item1.setDescription(item.getDescription());
			item1.setStatus(item.getStatus());
			item1.setStartDate(item.getStartDate());
			User user = userService.findUserByEmail(auth.getName());
			
			item.setAttachedfile(fullFilePath);
			item1.setAttachedfile(fullFilePath);
			Tracker tracker= trackerService.findById(id);
			trackerService.addItemsToTracker(item1, tracker, user);
			
			if (item.getFile().getOriginalFilename()!=null && item.getFile().getOriginalFilename().length() >3) {
			try {
				  bytes = item.getFile().getBytes();
				  Path path = Paths.get(fullFilePath);
		          Files.write(path, bytes);
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		
			
			List<TrackerItem> xx= trackerService.findById(id).getActionItems() ;
			
			modelAndView.addObject("item", item);
			modelAndView.addObject("actionItems",trackerService.findById(id).getActionItems() );
			modelAndView.setViewName("item");
			
		

			
		}
		modelAndView.addObject("role", util.getRole());	
		return modelAndView ;

}
	
	
	
	
	

	@RequestMapping(value = "/tasktracker/{id}")
	public ModelAndView viewtracker(@PathVariable int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		ModelAndView modelAndView = new ModelAndView();
		TrackerItem item= new TrackerItem();
		item.setTracker(trackerService.findById(id));
		modelAndView.addObject("item", item);
		modelAndView.addObject("actionItems",trackerService.findById(id).getActionItems() );
		modelAndView.setViewName("item");
		modelAndView.addObject("role", util.getRole());
		return modelAndView ;

}
	@RequestMapping(value = "/updatetracker/{id}")
	public ModelAndView edittracker( @PathVariable int id) {
		
		
		ModelAndView modelAndView = new ModelAndView();
	   // TrackerItem item= new TrackerItem();

		TrackerItem  item = trackerItemService.findById(id);
		
		modelAndView.addObject("item", item);
		modelAndView.addObject("actionItems",item.getTracker().getActionItems() );
		
		modelAndView.setViewName("updatetracker");
		
		modelAndView.addObject("role", util.getRole());
		return modelAndView;
	}
	
	@RequestMapping(value="/updatetracker/{id}", method = RequestMethod.POST)
	public ModelAndView editTracker(TrackerItem item,@PathVariable int id, BindingResult bindingResult){
	
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			
			TrackerItem  traskerItem = trackerItemService.findById(id);
			modelAndView.addObject("item", item);
			//modelAndView.addObject("role", util.getRole());
			modelAndView.addObject("actionItems",traskerItem.getTracker().getActionItems() );
			modelAndView.addObject("role", util.getRole());
			
			if (item.getTitle()==null ||item.getTitle().length()<1) {
				modelAndView.addObject("successMessage", "Please provide title");
			}
			if (item.getDescription()==null ||item.getDescription().length()<1) {
				modelAndView.addObject("successMessage", "Please provide description");
			}
			if ((item.getTitle()==null ||item.getTitle().length()<1) && (item.getDescription()==null ||item.getDescription().length()<1)){
				modelAndView.addObject("successMessage", "Please provide title and decription");
			}
			
		}else {
			
			TrackerItem  itemUpdate =trackerItemService.findById(id);
			itemUpdate.setDescription(item.getDescription());
			itemUpdate.setTitle(item.getTitle());
			itemUpdate.setStatus(item.getStatus());
			itemUpdate.setStartDate(new Date());
			//itemUpdate.setStartDate(item.getStartDate());
			
			String fullFilePath=null;
			byte[] bytes =null;
			if (item.getFile().getOriginalFilename()!=null && item.getFile().getOriginalFilename().length() >3) {
			 fullFilePath= "/home/sruthy/emptravel_upload_files/" +new Date().getTime()+ "_" + item.getFile().getOriginalFilename();
			itemUpdate.setAttachedfile(fullFilePath);
			}
			
			
			trackerItemService.saveItem(itemUpdate);
			
			
			
			if (item.getFile().getOriginalFilename()!=null &&  item.getFile().getOriginalFilename().length() >3) {
			
			try {
				  bytes = item.getFile().getBytes();
				  Path path = Paths.get(fullFilePath);
		          Files.write(path, bytes);
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
			modelAndView.addObject("successMessage", "Updated successfully.");
			modelAndView.addObject("item", itemUpdate);
			//modelAndView.addObject("role", util.getRole());
			modelAndView.addObject("actionItems",itemUpdate.getTracker().getActionItems() );
			modelAndView.addObject("role", util.getRole());
			modelAndView.setViewName("/updatetracker");
			
		}
		
		
		
		
	
	return modelAndView;
	}
	
	
	
	   @RequestMapping("/todo/attachmentdownload/{id}")
	    public void downloadPDFResource( HttpServletRequest request,
	                                     HttpServletResponse response,
	                                     @PathVariable("id") int id)
	    {
	        
	    	TrackerItem item= trackerItemService.findById(id);
	    	//If user is not authorized - he should be thrown out from here itself
	         
	        //Authorized user will download the file
	        String filePath = item.getAttachedfile();
	       
	        Path file = Paths.get(filePath);
	        
	        
	        String fileName=item.getAttachedfile().replaceAll("/home/sruthy/emptravel_upload_files/", "");
	        
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
	   
	
	
}