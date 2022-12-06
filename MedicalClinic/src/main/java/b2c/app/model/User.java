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
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import b2c.app.constraint.Login;
import b2c.app.constraint.UserPassword;

@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long id;
	@NotEmpty
	@Column(unique = true)
	@Login
	private String login;
	@NotEmpty
	@UserPassword
	private String password;
	@NotEmpty
	@Size(max = 20)
	private String firstName;
	@NotEmpty
	@Size(max = 20)
	private String lastName;
	@NotEmpty
	@Column(unique = true)
	private long pesel;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Set<UserRole> roles = new HashSet<>();
	@OneToMany(mappedBy = "")
	
	private List<Test> tests;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user", orphanRemoval= true)
	private List<PermissionForm> permissionForms;	
	
	@ManyToMany(mappedBy = "users")
	private List<MedicalResearchProject> researches = new ArrayList<>();
	
	public User() {};
	
	public User(String login, String password, String firstName, String lastName, long pesel) {
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pesel = pesel;
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

	public long getPesel() {
		return pesel;
	}

	public void setPesel(long pesel) {
		this.pesel = pesel;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	public List<PermissionForm> getPermissionForms() {
		return permissionForms;
	}

	public void setPermissionForms(List<PermissionForm> permissionForms) {
		this.permissionForms = permissionForms;
	}

	public List<MedicalResearchProject> getResearches() {
		return researches;
	}

	public void setResearches(List<MedicalResearchProject> researches) {
		this.researches = researches;
	}
}