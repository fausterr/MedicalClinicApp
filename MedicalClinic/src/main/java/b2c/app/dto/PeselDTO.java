package b2c.app.dto;

public class PeselDTO {
	private long pesel;
	
	public PeselDTO() {}
	
	public PeselDTO(long pesel) {
		this.pesel = pesel;
	}

	public long getPesel() {
		return pesel;
	}

	public void setPesel(long pesel) {
		this.pesel = pesel;
	}	
}