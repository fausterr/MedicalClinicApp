package b2c.app.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class PermissionForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_permission_form")
	private Long id;
	@Min(999999999)
	private long pesel;
	@NotEmpty
	@Size(max = 20)
	private String firstName;
	@NotEmpty
	@Size(max = 20)
	private String lastName;
	@Max(999999999)
	@Min(100000000)
	private long phoneNumber;
	@Min(18)
	private int age;
	@NotEmpty
	private String city;
	@NotEmpty
	private String address;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	private boolean permission;	
	
	public PermissionForm() {}
	
	public PermissionForm(long pesel, String firstName, String lastName, long phoneNumber, int age, String city,
			String address, boolean permission) {
		super();
		this.pesel = pesel;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.age = age;
		this.city = city;
		this.address = address;
		this.permission = permission;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public long getPesel() {
		return pesel;
	}
	
	public void setPesel(long pesel) {
		this.pesel = pesel;
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
	
	public long getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean isPermission() {
		return permission;
	}
	
	public void setPermission(boolean permission) {
		this.permission = permission;
	}
}