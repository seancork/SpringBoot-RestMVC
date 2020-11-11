package ie.son.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.son.entities.Bid;
import ie.son.entities.Job;
import ie.son.entities.User;
import ie.son.formobjects.BidForm;
import ie.son.services.BidService;
import ie.son.services.JobService;
import ie.son.services.UserService;

@Controller
public class BidController {
	
	@Autowired 
	BidService bidService;
	
	@Autowired 
	JobService jobService;
	
	@Autowired 
	UserService userService;
	
	@GetMapping("/newbid/{id}")
		public String addNewBidForm(@PathVariable(name="id") int id, Model model)
		{
		//check if job exists before allowing the user to bid on it
		Job job = jobService.findJob(id);
		if (job == null)
		{
			model.addAttribute("id", id);
			return "notfounderror";
		}
		
		  model.addAttribute("id", id);
		  model.addAttribute("bidForm", new BidForm());
		return "newbid";
		
	}
	
	@PostMapping("/newbid/{id}")
	public String addNewBidSave(@PathVariable(name="id") int id,@Valid BidForm bidForm, BindingResult binding, RedirectAttributes redirectAttributes,Principal user)
	{
		User creator = userService.findByEmail(user.getName());
		
		if (binding.hasErrors())
			return "newbid";
		
		Bid bid;
		if(jobService.OwnedByCurrentUser(id,  creator.getUserId()) == false && bidService.checkIfJobActive(id) == true && bidService.checkIfBidIsLower(bidForm.getBidAmount())== false) {
		bid = new Bid(bidForm.getBidAmount(),  jobService.findByJobId(id), userService.findByUserId(creator.getUserId())); 
		bid = bidService.save(bid);
		}else {
			if(jobService.OwnedByCurrentUser(id,  creator.getUserId())){
			redirectAttributes.addFlashAttribute("yourbid", true);
			}
			else if(bidService.checkIfJobActive(id) == false){
				redirectAttributes.addFlashAttribute("notactive", true);
				}
			else if(bidService.checkIfBidIsLower(bidForm.getBidAmount()) == true){
					redirectAttributes.addFlashAttribute("tohigh", true);
					}
			
			return "redirect:/newbid/"+id;
	}
		
		if (bid !=null )
			return "redirect:/showjob/"+id;
		else {
			redirectAttributes.addFlashAttribute("duplicate", true);
			return "redirect:/newbid/"+id;
		}
	}
}
