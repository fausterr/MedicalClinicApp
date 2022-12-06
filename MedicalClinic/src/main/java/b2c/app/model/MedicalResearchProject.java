package b2c.app.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class MedicalResearchProject implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_research")
	private Long id;
	
	@ManyToMany
	@JoinTable(name = "research_users",
			joinColumns = {@JoinColumn(name = "research_id", referencedColumnName = "id_research")},
			inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id_user")})
	private List<User> users;
	
	@ManyToMany
	@JoinTable(name = "research_doctors",
			joinColumns = {@JoinColumn(name = "research_id", referencedColumnName = "id_research")},
			inverseJoinColumns = {@JoinColumn(name = "doctor_id", referencedColumnName = "id_doctor")})
	private List<Doctor> doctors;
	private String name;
	private String description;
	private int participants;
	
	public MedicalResearchProject() {}
	
	public MedicalResearchProject(String name, String description, int participants) {
		this.name = name;
		this.description = description;
		this.participants = participants;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}
	
	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public int getParticipants() {
		return participants;
	}

	public void setParticipants(int participants) {
		this.participants = participants;
	}
}