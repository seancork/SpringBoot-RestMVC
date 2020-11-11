package ie.son.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

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
import ie.son.formobjects.JobForm;
import ie.son.services.BidService;
import ie.son.services.JobService;
import ie.son.services.UserService;

@Controller
public class JobController {

	@Autowired 
	JobService jobService;
	
	@Autowired 
	UserService userService;
	
	@Autowired 
	BidService bidService;
	
	@GetMapping("/showjobs") 
	public String showJobs(Model model)
	{
		List<Job> jobs = jobService.listInOrder();
		model.addAttribute("jobs", jobs);
		return "jobs";
	} 
	
	@GetMapping("/showjob/{id}") 
	public String showAJob(@PathVariable(name="id") int id, Model model)
	{
		List<Bid> bids = bidService.findBidsWithJobId(id);
		Job job = jobService.findJob(id);
		int lowest = bidService.getlowestBid(id);
		
		if (job == null)
		{
			model.addAttribute("id", id);
			return "notfounderror";
		}
		model.addAttribute("lowestbid", lowest);
		model.addAttribute("job", job);
		model.addAttribute("bids", bids);
	
		return "job";
	}
	
	@GetMapping("/newjob")
	public String addNewJobForm(Model model)
	{
		model.addAttribute("jobForm", new JobForm());
		return "newjob";
	}
	
	@PostMapping("/newjob")
	public String addNewJobSave(@Valid JobForm jobForm, BindingResult binding, RedirectAttributes redirectAttributes,Principal user)
	{
		if (binding.hasErrors())
			return "newjob";
	
		LocalDate localDate = LocalDate.now();
		Job job = new Job(jobForm.getJobName(), jobForm.getDescription(), localDate, userService.findByEmail(user.getName()), true);
		job = jobService.save(job);
		
		if (job !=null )
			return "redirect:showjob/"+job.getJobId();
		else {
			// starts again with empty form (new object)
			redirectAttributes.addFlashAttribute("duplicate", true);
			return "redirect:newjob";
		}
	}	
}
