package ie.son;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RestMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestMvcApplication.class, args);
	}

}
