package b2c.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import b2c.app.model.Doctor;
import b2c.app.model.UserRole;
import b2c.app.repository.DoctorRepo;
import b2c.app.repository.UserRoleRepo;

@Service
public class DoctorService {

	private static final String ADMIN_ROLE = "ROLE_ADMIN";
	private DoctorRepo doctorRepo;
	private UserRoleRepo userRoleRepo;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public DoctorService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Autowired
	public void setDoctorRepo(DoctorRepo doctorRepo) {
		this.doctorRepo = doctorRepo;
	}

	@Autowired
	public void setUserRoleRepo(UserRoleRepo userRoleRepo) {
		this.userRoleRepo = userRoleRepo;
	}

	public void addDoctor(Doctor doctor) {
		UserRole adminRole = userRoleRepo.findByRole(ADMIN_ROLE);
		doctor.getRoles().add(adminRole);
		String passwordHash = passwordEncoder.encode(doctor.getPassword());
		doctor.setPassword(passwordHash);
		doctorRepo.save(doctor);
	}
	
	public Doctor findById(long id) {
		Doctor doctor = doctorRepo.findById(id);
		return doctor;
	}
}