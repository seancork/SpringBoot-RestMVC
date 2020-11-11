package ie.son.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.son.entities.Role;
import ie.son.entities.User;
import ie.son.formobjects.RegisterForm;
import ie.son.services.RoleService;
import ie.son.services.UserService;

@Controller
public class RegisterController {
	
	@Autowired 
	UserService userService;
	
	@Autowired 
	RoleService RoleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping(value = "/register")
	public String register(Model model)
	{
		model.addAttribute("registerForm", new RegisterForm());
		return "register";
	}
    
	@PostMapping("/register")
	public String regsiterUserSave(@Valid RegisterForm registerForm, BindingResult binding, RedirectAttributes redirectAttributes)
	{
		if (binding.hasErrors())
			return "register";
		
		//Create default user role for the user that is registering 
		Role role = new Role(registerForm.getEmail(), "ROLE_USER");
		role = RoleService.save(role);
		
		//saving user to database
		User user = new User(registerForm.getEmail(), registerForm.getName(),passwordEncoder.encode(registerForm.getPassword()),registerForm.getPhonenum(), true, role);
		user = userService.save(user);
		
		if (user !=null ) {
			redirectAttributes.addFlashAttribute("success", true);
			return "redirect:register";
		}else {
			redirectAttributes.addFlashAttribute("duplicate", true);
			return "redirect:register";
		}		
	}	
}
