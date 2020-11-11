package ie.son.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.son.dao.BidDao;
import ie.son.dao.JobDao;
import ie.son.entities.Bid;

@Service
public class BidServiceImplementation implements BidService {

	@Autowired 
	BidDao bidDao;
	
	@Autowired 
	JobDao jobDao;
	
	@Override
	public Bid findBid(int id) {
		if (bidDao.existsById(id))
			return bidDao.findById(id).get();
		return null;
	}

	@Override
	public Bid save(Bid bid) {	
		return bidDao.save(bid);
	}

	@Override
	public List<Bid> findBidsWithJobId(int jobid) {
		if (jobDao.existsByJobId(jobid))
			return bidDao.findBidsWithJobId(jobid);
		
		return null;
	}
	
	@Override
	public boolean checkIfJobActive(int jobid) {
		if (bidDao.checkIfJobActive(jobid)){
		return true;
	}else {
		return false;
	}
}
	
	@Override
	public boolean checkIfBidIsLower(int bidAmount) {	
		//this checks if bid is lower than current bid, if it comes back null it means there is no current bid.
		if (bidDao.checkIfBidIsLower(bidAmount) == "true" || bidDao.checkIfBidIsLower(bidAmount) != null){
		return true;
	}else {
		return false;
	}
}

	@Override
	public int getlowestBid(int jobid) {
		return bidDao.getlowestBid(jobid);
	}
	@Override
	public List<Bid> findBidsWithUserId(int userid){
		return bidDao.findBidsWithUserId(userid);
  }
}
