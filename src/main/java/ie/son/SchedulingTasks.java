package ie.son;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ie.son.services.JobService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SchedulingTasks {
	
	@Autowired
	JobService jobService;
	
	//Runs everyday
	@Scheduled(cron = "0 02 13 * * *")
	public void closeProjects()
	{
		log.info("Closeing all jobs that are 20+ days old day :)");

       //get date of 20 days ago 
		jobService.schedulingTasks(LocalDate.now().minusDays(20));	
		
	}
	
}
