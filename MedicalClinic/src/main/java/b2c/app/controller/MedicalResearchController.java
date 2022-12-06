package b2c.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import b2c.app.model.MedicalResearchProject;
import b2c.app.model.PermissionForm;
import b2c.app.model.User;
import b2c.app.service.MedicalResearchProjectService;
import b2c.app.service.UserService;

@Controller
public class MedicalResearchController {

	private MedicalResearchProjectService medicalResearchProjectService;
	private UserService userService;
	
	@Autowired
	public MedicalResearchController(MedicalResearchProjectService medicalResearchProjectService,
			UserService userService) {
		this.medicalResearchProjectService = medicalResearchProjectService;
		this.userService = userService;
	}
	
	@GetMapping("/medical_researches")
	public String getAllAvailableResearches(Model model) {
		model.addAttribute("projects", medicalResearchProjectService.getAllProjects());
		return "availableResearches";
	}
	
	@GetMapping("/research/{id}")
	public String getSingleResearchProject(@PathVariable("id") int id, Model model) {
		MedicalResearchProject project = medicalResearchProjectService.findById(id);
		User user = userService.getUserByLogin(UserService.getCurrentUser());
		project.getUsers().add(user);
		medicalResearchProjectService.saveNewMedicalResearchProject(project);
		model.addAttribute("permissionForm", new PermissionForm());
		return "permissionForm";
	}	
}