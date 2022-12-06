package b2c.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import b2c.app.model.PermissionForm;
import b2c.app.repository.PermissionFormRepo;

@Service
public class PermissionFormService {
	
	private PermissionFormRepo permissionFormRepo;
	
	@Autowired
	public PermissionFormService(PermissionFormRepo permissionFormRepo) {
		this.permissionFormRepo = permissionFormRepo;
	}
	
	public void savePermissionForm(PermissionForm permissionForm) {
		permissionFormRepo.save(permissionForm);
	}
	
	public PermissionForm findByPesel(long pesel) {
		PermissionForm pf = permissionFormRepo.findByPesel(pesel);
		return pf;
	}
}