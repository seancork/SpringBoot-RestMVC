package ie.son.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping(value = "/login")
	public String login()
	{
		return "login";
	}
    
	@GetMapping(value = "/403")
	public String PrventAccess()
	{
		return "403";
	}
}
