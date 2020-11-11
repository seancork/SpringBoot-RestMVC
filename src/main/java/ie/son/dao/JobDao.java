package ie.son.dao;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ie.son.entities.Job;

public interface JobDao extends JpaRepository<Job, Integer> {
	Job findByJobId(int jobId);
	
	boolean existsByJobId(int jobId);

	List<Job> findAllByOrderByJobNameAsc();
	 @Query("SELECT c.jobName FROM Job c where c.jobId = :id") 
	 String findNameOfCountyById(@Param("id") int id);

	@Query("SELECT c FROM Job c JOIN Bid t ON t.job=c WHERE t.bidId=:BidId")
	List<Job> findJobsWithBidId(@Param("BidId") String bidName);
	
	 @Query("select count(e)>0 from Job e where e.jobId = :jobid AND e.user.id = :userid ")
	 boolean OwnedByCurrentUser(int jobid, int userid );
	
	 @Query("SELECT u FROM Job u WHERE u.jobActive = true")
	 List<Job> findAllActiveJobs();
	 
	 @Transactional
	 @Modifying									  
	 @Query("UPDATE Job Set jobactive = false where TIMESTAMP  <:timeStamp")
	 void schedulingTasks(LocalDate timeStamp);
}
