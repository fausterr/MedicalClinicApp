package b2c.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import b2c.app.model.PermissionForm;
import b2c.app.service.PermissionFormService;
import b2c.app.service.UserService;

@Controller
public class PermissionFormController {
	
	private PermissionFormService permissionFormService;
	private UserService userService;
	
	public PermissionFormController(PermissionFormService permissionFormService, UserService userService) {
		this.permissionFormService = permissionFormService;
		this.userService = userService;
	}	
	
	@PostMapping("/permission_form")
	public String postPermissionForm(@ModelAttribute PermissionForm permissionForm) {
		permissionForm.setUser(userService.getUserByLogin(UserService.getCurrentUser()));
		permissionForm.setPermission(true);
		permissionFormService.savePermissionForm(permissionForm);
		return "zzz";
	}
}