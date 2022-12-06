package b2c.app.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import b2c.app.model.MedicalResearchProject;
import b2c.app.model.User;

@Repository
public interface MedicalResearchProjectRepo extends JpaRepository<MedicalResearchProject, Long>{
	
	public MedicalResearchProject findById(long id); 
	
	@Modifying
	@Transactional
	@Query("UPDATE MedicalResearchProject m SET m.name = :name, m.description = :description, m.participants = :participants WHERE m.id = :id")
	public void updateProject(@Param(value = "id") long id,
			@Param(value = "name") String name,
			@Param(value = "description") String description,
			@Param(value = "participants") int participants);
	
	@Modifying
	@Transactional
	@Query("UPDATE MedicalResearchProject m set m.users = :users WHERE m.id = :id")
	public void addUserToProject(@Param(value = "id") long id,
			@Param(value = "users") List<User> users);
		
	public MedicalResearchProject findByName(String name);	
}