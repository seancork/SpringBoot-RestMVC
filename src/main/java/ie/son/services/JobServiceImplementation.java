package ie.son.services;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.son.dao.JobDao;
import ie.son.entities.Job;

@Service
public class JobServiceImplementation implements JobService {

	@Autowired 
	JobDao jobDao;
	
	@Override
	public Job findJob(int id) {
		if (jobDao.existsById(id))
			return jobDao.findById(id).get();
		return null;
	}

	@Override
	public Job findByJobId(int jobId) {
		if (jobDao.existsById(jobId))
			return jobDao.findByJobId(jobId);
		return null;
	}

	@Override
	public List<Job> listInOrder() {
		return jobDao.findAll();		
	} 

	@Override
	public List<Job> findJobsWithBidId(String BidId) {
		return jobDao.findJobsWithBidId(BidId);
	}

	@Override
	public Job save(Job job) {
		return jobDao.save(job);
	}
	
	@Override
	public boolean OwnedByCurrentUser(int jobid, int userid ) {
		if (jobDao.OwnedByCurrentUser(jobid, userid)){
		return true;
	}else {
		return false;
	}
}

	@Override
	public List<Job> findAllActiveJobs() {
		return jobDao.findAllActiveJobs();
	}

	@Override
	public void schedulingTasks(LocalDate timeStamp) {
		jobDao.schedulingTasks(timeStamp);
	}
	}

