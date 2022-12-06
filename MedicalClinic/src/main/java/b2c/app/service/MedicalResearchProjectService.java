package b2c.app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import b2c.app.model.MedicalResearchProject;
import b2c.app.model.User;
import b2c.app.repository.MedicalResearchProjectRepo;

@Service
public class MedicalResearchProjectService {

	public MedicalResearchProjectRepo medicalResearchProjectRepo;

	@Autowired
	public void setMedicalResearchProjectRepo(MedicalResearchProjectRepo medicalResearchProjectRepo) {
		this.medicalResearchProjectRepo = medicalResearchProjectRepo;
	}
	
	public void saveNewMedicalResearchProject(MedicalResearchProject research) {
		medicalResearchProjectRepo.save(research);
	}
	
	public List<MedicalResearchProject> getAllProjects() {
		return medicalResearchProjectRepo.findAll();
	}
	
	public MedicalResearchProject findById(long id) {
		return medicalResearchProjectRepo.findById(id);
	}
	
	public void updateProject(long id, String name, String description, int participants) {
		medicalResearchProjectRepo.updateProject(id, name, description, participants);
	}
	
	public void deleteProject(long id) {  
		medicalResearchProjectRepo.deleteById(id);
	}
	
	public void updateWithUser(long id, List<User> users) {
		medicalResearchProjectRepo.addUserToProject(0, users);
	}
	
	public MedicalResearchProject findByName(String name) {
		return medicalResearchProjectRepo.findByName(name);
	}
}