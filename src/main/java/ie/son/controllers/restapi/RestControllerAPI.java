package ie.son.controllers.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ie.son.entities.Bid;
import ie.son.entities.Job;
import ie.son.services.BidService;
import ie.son.services.JobService;

@RestController
@RequestMapping("/api")
public class RestControllerAPI {

	@Autowired
	private JobService jobService;
	
	@Autowired
	private BidService bidService;
	
	//List of jobs with active status(aka true)
	@PreAuthorize("hasRole('API')")
	@GetMapping("/allactivejobs")
	public List<Job> findAllActiveJobs(){
		return jobService.findAllActiveJobs();
	}
	
	//List all bids by a user id
	@PreAuthorize("hasRole('API')")
	@GetMapping("/bidsbyuserid/{id}")
	public List<Bid> findAllBidsWithUserId(@PathVariable("id") int userid){
		return bidService.findBidsWithUserId(userid);
	}
}
