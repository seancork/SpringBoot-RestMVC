package ie.son.services;

import java.util.List;

import ie.son.entities.Bid;

public interface BidService {
	
	boolean checkIfJobActive(int jobid);
	boolean checkIfBidIsLower(int bidAmount);
	
	Bid findBid(int id);
	
	List<Bid> findBidsWithJobId(int BidId);
	List<Bid> findBidsWithUserId(int userid);
	
	Bid save(Bid bid);
	
	int getlowestBid(int jobid);
	
	
}
