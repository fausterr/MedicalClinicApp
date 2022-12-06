package b2c.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import b2c.app.model.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long>{
	
	public Doctor findById(long id);
}