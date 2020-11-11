package ie.son.services;

import java.time.LocalDate;
import java.util.List;

import ie.son.entities.Job;

public interface JobService {
	Job findJob(int id);
	Job findByJobId(int JobId);
	List<Job> listInOrder(); 
	List<Job> findJobsWithBidId(String BidId);
	Job save(Job job);
	boolean OwnedByCurrentUser(int jobid, int userid);
	List<Job> findAllActiveJobs();
	void schedulingTasks(LocalDate pastdate);
}
