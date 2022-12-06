package b2c.app.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import b2c.app.model.Test;
import b2c.app.service.TestService;
import b2c.app.service.UserService;

@Controller
public class UserProfileController {

	private UserService userService;
	private TestService testService;
	
	public UserProfileController(UserService userService, TestService testService) {
		this.userService = userService;
		this.testService = testService;
	}
	
	@GetMapping("/profile")
	public String getUserProfile(Model model) {
		String login = UserService.getCurrentUser();
		model.addAttribute("user", userService.getUserByLogin(login));
		boolean isUser = UserService.hasRole("ROLE_USER");
		boolean isAdmin = UserService.hasRole("ROLE_ADMIN");
		boolean isSuperAdmin = UserService.hasRole("ROLE_SUPERADMIN");
		model.addAttribute("isUser", isUser);
		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("isSuperAdmin", isSuperAdmin);
		model.addAttribute("user", userService.getUserByLogin(login));
		return "userProfile";
	}
	
	@GetMapping("/profile/active_tests")
	public String getActiveTests(Model model ) {
		Long userId = userService.getUserByLogin(UserService.getCurrentUser()).getId();
		List<Test> activeTests = testService.getAllActiveTestByUserId(userId);
		model.addAttribute("tests", activeTests);
		return "activeTests";
	}
	
	@GetMapping("/profile/previous_tests")
	public String getHistoryTests(Model model) {
		Long userId = userService.getUserByLogin(UserService.getCurrentUser()).getId();
		List<Test> inactiveTests = testService.getAllInctiveTestByUserId(userId);
		model.addAttribute("tests", inactiveTests);
		return "inactiveTests";
	}
}