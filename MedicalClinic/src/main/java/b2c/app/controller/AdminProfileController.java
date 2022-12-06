package b2c.app.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import b2c.app.dto.PeselDTO;
import b2c.app.model.MedicalResearchProject;
import b2c.app.model.PermissionForm;
import b2c.app.model.Test;
import b2c.app.model.User;
import b2c.app.service.DoctorService;
import b2c.app.service.MedicalResearchProjectService;
import b2c.app.service.PermissionFormService;
import b2c.app.service.TestService;
import b2c.app.service.UserService;

@Controller
public class AdminProfileController {

	private UserService userService;
	private TestService testService;
	private MedicalResearchProjectService medicalResearchProjectService;
	private DoctorService doctorService;
	private PermissionFormService permissionFormService;

	@Autowired
	public AdminProfileController(UserService userService, TestService testService,
			MedicalResearchProjectService medicalResearchProjectService,
			DoctorService doctorService, PermissionFormService permissionFormService) {
		this.userService = userService;
		this.testService = testService;
		this.medicalResearchProjectService = medicalResearchProjectService;
		this.doctorService = doctorService;
		this.permissionFormService = permissionFormService;
	}
	
	@GetMapping("/a")
	public String getSuperAdminProfile() {
		return "adminProfile";
	}
	
	@GetMapping("/a/create_test")
	public String createTest(Model model) {
		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("pesel", new PeselDTO());
		modelMap.put("test", new Test());
		model.addAllAttributes(modelMap);
		return "testForm";
	}
	
	@PostMapping("/a/create_test")
	public String createTest(@ModelAttribute PeselDTO pesel, @ModelAttribute Test test) {
		User user = userService.getUserByPesel(pesel.getPesel()); //tu sprawdzic czy istnieje
		test.setUser(user);
		test.setActive(true);
		testService.saveTest(test);
		return "zzz";
	}
	
	@GetMapping("/a/updateTest")
	public String updateTest(Model model) {
		return "updateTest";
	}
	
	@PostMapping("/a/updateTest") //dubluje zamiast modyfikowac
	public String updateTest(@RequestParam("orderNumber") long orderNumber,
			@RequestParam("result")String result) {
		Test test = testService.findByOrderNumber(orderNumber);
		test.setResult(result);
		testService.saveTest(test);
		return "zzz";
	}
	
	//-----------MRP
	
	@GetMapping("/a/create_research")
	public String createNewResearchForm(Model model) {
		model.addAttribute("research", new MedicalResearchProject());
		return "researchForm";
	}
	
	@PostMapping("/a/create_research")
	public String createNewResearchForm(@ModelAttribute MedicalResearchProject research) {
		medicalResearchProjectService.saveNewMedicalResearchProject(research);
		return "zzz";
	}
	
	@GetMapping("/a/researches")
	public String getAllResearchProjects(Model model) {
		model.addAttribute("projects", medicalResearchProjectService.getAllProjects());
		return "adminResearches";
	}
	
	@GetMapping("/a/research/{id}")
	public String getSingleResearchProject(@PathVariable("id") int id, Model model) {
		MedicalResearchProject project = medicalResearchProjectService.findById(id);
		model.addAttribute("project", project);
		return "project";
	}
	
	@PostMapping("/a/research/update")
	public String editSave(@ModelAttribute MedicalResearchProject project) {
		System.out.println(project.getId());
		medicalResearchProjectService.updateProject(
				project.getId(), 
				project.getName(), 
				project.getDescription(),
				project.getParticipants());
		return "zzz";
	}
	
	@GetMapping("/a/delete_research/{id}")
	public String getSingleResearchProjectToDelete(@PathVariable("id") int id, Model model) {
		MedicalResearchProject project = medicalResearchProjectService.findById(id);
		model.addAttribute("project", project);
		return "projectDelete";
	}
	
	@PostMapping("/a/research/delete") 
	public String removeProject(@ModelAttribute MedicalResearchProject project) {
		medicalResearchProjectService.deleteProject(project.getId());
		return "zzz";
	}
	
	@GetMapping("/a/researches/remove_user")
	public String removeUserFromProject() {
		return "removeUserFromResearch";
	}

	@PostMapping("/a/researches/remove_user")
	public String removeUserFromProject(@RequestParam("pesel") long pesel,
			@RequestParam("r_name")String r_name) {
		MedicalResearchProject project = medicalResearchProjectService.findByName(r_name);
		project.getUsers().remove(userService.getUserByPesel(pesel));
		medicalResearchProjectService.saveNewMedicalResearchProject(project);
		PermissionForm pf = permissionFormService.findByPesel(pesel);
		pf.setPermission(false);
		permissionFormService.savePermissionForm(pf);
		return "zzz";
	}
	
	@GetMapping("/a/researches/add_doctor")
	public String addDoctorToProject() {
		return "addDoctorToProject";
	}
	
	@PostMapping("/a/researches/add_doctor")
	public String addDoctorToProject(@RequestParam("id") long id,
			@RequestParam("r_name")String r_name) {
		MedicalResearchProject project = medicalResearchProjectService.findByName(r_name);
		project.getDoctors().add(doctorService.findById(id));
		medicalResearchProjectService.saveNewMedicalResearchProject(project);
		return "zzz";
	}

	@GetMapping("/a/researches/remove_doctor")
	public String removeDoctorFromProject() {
		return "addDoctorToProject";
	}
	
	@PostMapping("/a/researches/remove_doctor")
	public String removeDoctorFromProject(@RequestParam("id") long id,
			@RequestParam("r_name")String r_name) {
		MedicalResearchProject project = medicalResearchProjectService.findByName(r_name);
		project.getDoctors().remove(doctorService.findById(id));
		medicalResearchProjectService.saveNewMedicalResearchProject(project);
		return "zzz";
	}
	
	
	//-----------
	
	public String updateUserInformation() {
		return "";
	}	
}