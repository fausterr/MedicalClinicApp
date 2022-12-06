package b2c.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import b2c.app.model.User;
import b2c.app.service.UserService;

@Controller
public class RegisterController {

	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("user", new User());
		return "registerForm";
	}
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute User user) {
		userService.addDefaultUser(user);
		return "registerSuccess";
	}
}