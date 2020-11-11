package ie.son;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ie.son.entities.Bid;
import ie.son.entities.Job;
import ie.son.entities.Role;
import ie.son.entities.User;
import ie.son.services.BidService;
import ie.son.services.JobService;
import ie.son.services.RoleService;
import ie.son.services.UserService;

// Load data into the database. 
@Component
public class DataLoader implements ApplicationRunner {
    
	@Autowired
	RoleService roleService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	JobService jobService;
	
	@Autowired
	BidService bidService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
    LocalDateTime datetime1 = LocalDateTime.now();  
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		//role data
		Role role1 = new Role("contact@seanoneill.me", "ROLE_USER");
		roleService.save(role1);
		
		Role role2 = new Role("cat1@mycit.ie", "ROLE_USER");
		roleService.save(role2);
		
		Role role1_api = new Role("test@mycit.ie", "ROLE_API");
		roleService.save(role1_api);
		
		//user data
		User user1_data = new User("contact@seanoneill.me"," Sean O' Neill", passwordEncoder.encode("password"), "0894288120",true,role1);
		userService.save(user1_data);
		
		User user2_data = new User("cat1@mycit.ie"," Cat Cat", passwordEncoder.encode("password"), "0894738222",true,role2);
		userService.save(user2_data);
		
		User user3_data = new User("test@mycit.ie"," Test me", passwordEncoder.encode("password"), "0848393839",true,role1_api);
		userService.save(user3_data);
		
		//used to test scheduling is working
		//LocalDate date = LocalDate.now().minusDays(21);
		
		LocalDate date = LocalDate.now();
		Job job1_data = new Job("Building new wall to half the kitchen", "Kitchen Wall", date, user2_data, true );
		jobService.save(job1_data);
		
		Job job2_data = new Job("Fitting new fireplace", "Fireplace", date, user3_data, true );
		jobService.save(job2_data);
		
		//get month ago date:
		 LocalDate monthago = LocalDate.now().minusDays(30);
		Job job3_data = new Job("Fixing up bathroom", "Small bathroom", monthago, user2_data, false );
		jobService.save(job3_data);
		
		//bid data
		Bid bid1_data = new Bid(2000, job1_data, user1_data);
		bidService.save(bid1_data);
		
		Bid bid2_data = new Bid(3000, job1_data, user2_data);
		bidService.save(bid2_data);
		
		Bid bid3_data = new Bid(800, job2_data, user2_data);
		bidService.save(bid3_data);
		
		Bid bid4_data = new Bid(2000, job2_data, user2_data);
		bidService.save(bid4_data);
		
		Bid bid5_data = new Bid(3000, job2_data, user3_data);
		bidService.save(bid5_data);
		
		Bid bid6_data = new Bid(800, job2_data, user3_data);
		bidService.save(bid6_data);
	}
}
