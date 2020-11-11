package ie.son.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ie.son.entities.Bid;

public interface BidDao extends JpaRepository<Bid, Integer> {
	Bid findByBidId(int bidId);
	boolean existsByBidId(int bidId);

	 @Query("SELECT c FROM Bid c where c.job.id = :id") 
	 List<Bid> findBidsWithJobId(@Param("id") int id);

	 @Query("select count(e)>0 from Job e where e.jobId = :jobid AND jobActive = true ")
	 boolean checkIfJobActive(int jobid);

	 @Query("SELECT count(e)>0  FROM Bid e GROUP BY e.bidAmount HAVING min(e.bidAmount) <= :bidAmount")
	 String checkIfBidIsLower(int bidAmount);
	 
	//if there is no colums it will return null, but since im using int, i change null to 0 using COALESCE
	 @Query("SELECT  COALESCE(MIN(bidAmount),0) FROM Bid where job.id = :jobid")
	 int getlowestBid(int jobid);
   
	 @Query("SELECT c FROM Bid c where c.user.id = :userid") 
	  List<Bid> findBidsWithUserId(int userid);
	
}
