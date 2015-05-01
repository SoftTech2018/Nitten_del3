package dtu.shared;

import java.io.Serializable;
import java.util.List;

public class ReceptViewDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int receptId;
	private String receptNavn;
	private List<IngrediensDTO> ingredienser;
	
	public ReceptViewDTO(){
		
	}
	
	public ReceptViewDTO(int receptId, String receptNavn, List<IngrediensDTO> ingredienser){
		this.receptId = receptId;
		this.receptNavn = receptNavn;
		this.ingredienser = ingredienser;
	}

	public int getReceptId() {
		return receptId;
	}

	public String getReceptNavn() {
		return receptNavn;
	}
	
	public List<IngrediensDTO> getIngredienser() {
		return ingredienser;
	}

	public void setIngredienser(List<IngrediensDTO> ingredienser) {
		this.ingredienser = ingredienser;
	}

	public void setReceptId(int receptId) {
		this.receptId = receptId;
	}

	public void setReceptNavn(String receptNavn) {
		this.receptNavn = receptNavn;
	}

}
