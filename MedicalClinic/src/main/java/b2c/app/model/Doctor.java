package b2c.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import b2c.app.constraint.Login;
import b2c.app.constraint.UserPassword;

@Entity
public class Doctor implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_doctor")
	private Long id;
	@NotEmpty
	@Login
	private String login;
	@UserPassword
	private String password;
	@NotEmpty
	@Size(max = 20)
	private String firstName;
	@NotEmpty
	@Size(max = 20)
	private String lastName;
	@NotEmpty
	@Size(max = 20)
	private String specialization;
	@Max(999999999)
	@Min(100000000)
	private int phoneNumber;
	@PositiveOrZero
	private int office;
	
	@ManyToMany(mappedBy = "doctors")
	private List<MedicalResearchProject> researches = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Set<UserRole> roles = new HashSet<>();
	
	public Doctor() {}
	
	public Doctor(String login, String password, String firstName, String lastName,
			String specialization, int phoneNumber, int office) {
		super();
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.specialization = specialization;
		this.phoneNumber = phoneNumber;
		this.office = office;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getSpecialization() {
		return specialization;
	}
	
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public int getOffice() {
		return office;
	}
	
	public void setOffice(int office) {
		this.office = office;
	}
	
	public List<MedicalResearchProject> getResearches() {
		return researches;
	}
	
	public void setResearches(List<MedicalResearchProject> researches) {
		this.researches = researches;
	}
	
	public Set<UserRole> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}	
}