package dtu.shared;

import java.io.Serializable;

public class IngrediensDTO implements Serializable {
	
	private String navn;
	private double netto;
	
	public IngrediensDTO(){
		
	}
	
	public IngrediensDTO(String navn, double netto){
		this.navn = navn;
		this.netto = netto;
	}

	public String getNavn() {
		return navn;
	}

	public double getNetto() {
		return netto;
	}

}
