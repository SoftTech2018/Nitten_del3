package dtu.shared;

import java.io.Serializable;

public class ProdBatchInfo implements Serializable{

	private String recept_navn, opr_navn;
	private int recept_id, pb_id, opr_id, status;
	private double netto;
	
	public ProdBatchInfo(){
		
	}
	
	public ProdBatchInfo(int recept_id, String recept_navn, double netto, int pb_id, int opr_id, String opr_navn, int status){
		this.recept_id = recept_id;
		this.recept_navn = recept_navn;
		this.netto = netto;
		this.pb_id = pb_id;
		this.opr_id = opr_id;
		this.opr_navn = opr_navn;
		this.status = status;
	}
	
	public String getRecept_navn() {
		return recept_navn;
	}
	public void setRecept_navn(String recept_navn) {
		this.recept_navn = recept_navn;
	}
	public String getOpr_navn() {
		return opr_navn;
	}
	public void setOpr_navn(String opr_navn) {
		this.opr_navn = opr_navn;
	}
	public int getRecept_id() {
		return recept_id;
	}
	public void setRecept_id(int recept_id) {
		this.recept_id = recept_id;
	}
	public int getPb_id() {
		return pb_id;
	}
	public void setPb_id(int pb_id) {
		this.pb_id = pb_id;
	}
	public int getOpr_id() {
		return opr_id;
	}
	public void setOpr_id(int opr_id) {
		this.opr_id = opr_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getNetto() {
		return netto;
	}
	public void setNetto(double netto) {
		this.netto = netto;
	}

}
