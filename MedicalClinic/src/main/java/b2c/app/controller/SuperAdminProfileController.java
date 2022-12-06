package b2c.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import b2c.app.model.Doctor;
import b2c.app.service.DoctorService;
import b2c.app.service.UserService;

@Controller
public class SuperAdminProfileController {
	
	private DoctorService doctorService;
	private UserService userService;
	
	public SuperAdminProfileController(DoctorService doctorService, UserService userService) {
		this.doctorService = doctorService;
		this.userService = userService;
	}
	
	@GetMapping("/sa")
	public String getSuperAdminProfile() {	
		return "superAdminProfile";
	}
	
	@GetMapping("/sa/doctor_registration")
	public String getDoctorRegistration(Model model) {
		model.addAttribute("doctor", new Doctor());
		return "doctorRegistration";
	}
	
	@PostMapping("/sa/doctor_registration")
	public String postDoctorRegistration(@ModelAttribute Doctor doctor) { 
		doctorService.addDoctor(doctor);
		return "zzz";
	}
	
	@GetMapping("sa/remove_user")
	public String removeUser() {	
		return "removeUserFromApp";
	}
	
	@PostMapping("sa/remove_user")
	public String removeUser(@RequestParam("pesel") long pesel) {
		userService.deleteByPesel(pesel);
		return "removeUserFromApp";
	}	
}