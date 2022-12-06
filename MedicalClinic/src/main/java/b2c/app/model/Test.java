package b2c.app.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Test implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_test")
	private Long id;
	private long orderNumber;
	private String testName;
	private String measuringSpan;
	private String result;
	private boolean active;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate testDate;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Test() {}
	
	public Test(long orderNumber, String testName, String measuringSpan) {
		this.orderNumber = orderNumber;
		this.testName = testName;
		this.measuringSpan = measuringSpan;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public long getOrderNumber() {
		return orderNumber;
	}
	
	public void setOrderNumber(long orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public String getTestName() {
		return testName;
	}
	
	public void setTestName(String testName) {
		this.testName = testName;
	}
	
	public String getMeasuringSpan() {
		return measuringSpan;
	}
	
	public void setMeasuringSpan(String measuringSpan) {
		this.measuringSpan = measuringSpan;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public LocalDate getTestDate() {
		return testDate;
	}
	
	public void setTestDate(LocalDate testDate) {
		this.testDate = testDate;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}