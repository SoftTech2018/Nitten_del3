package dtu.shared;

import java.io.Serializable;

public class PersonDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String navn, password;
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private int alder;
	
	// default constructor - must be defined
	public PersonDTO() {
		
	}
	
	public PersonDTO(String navn, int alder) {
		super();
		this.navn = navn;
		this.alder = alder;
	}
	
	public PersonDTO(int id, String navn, int alder) {
		super();
		this.id = id;
		this.navn = navn;
		this.alder = alder;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getNavn() {
		return navn;
	}
	public void setNavn(String navn) {
		this.navn = navn;
	}
	
	public int getAlder() {
		return alder;
	}
	
	public void setAlder(int alder) {
		this.alder = alder;
	}
}
