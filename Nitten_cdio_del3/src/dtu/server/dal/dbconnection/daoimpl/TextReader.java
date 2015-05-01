package dtu.server.dal.dbconnection.daoimpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import com.google.gwt.core.client.GWT;

import dtu.shared.OperatoerDTO;
import dtu.server.dal.dbconnection.dto.ProduktBatchDTO;
import dtu.server.dal.dbconnection.dto.ProduktBatchKompDTO;
import dtu.server.dal.dbconnection.dto.RaavareBatchDTO;
import dtu.server.dal.dbconnection.dto.RaavareDTO;
import dtu.server.dal.dbconnection.dto.ReceptDTO;
import dtu.server.dal.dbconnection.dto.ReceptKompDTO;

public class TextReader {
	
	private File sqlFileCommands;
	private String[] sqlCommands;
	private String illegalString;
	
	public TextReader(){
//		sqlFileCommands = new File("sqlCommands.txt");
//		sqlCommands = readFile(sqlFileCommands);
		illegalString = "#";
	}
	
	private String[] readFile(File fil){
		List<String> data = new ArrayList<String>();
		String linje = null;
		data.add(null);
		try (BufferedReader br = new BufferedReader(new FileReader(fil));){
			while ((linje = br.readLine()) != null){
				data.add(linje);
			}
		} catch (IOException e) {
//			throw new FileNotFoundException();
		}
		return data.toArray(new String[data.size()]);
	}
	
	/**
	 * Returnerer den kommando der svarer til teksten på linje-nummeret i filen "files/sqlCommands.txt". 
	 * @param cmd Linjenummeret der skal returneres
	 * @return Teksten på linjenummeret
	 */
	public String getCommand(int cmd){
		return sqlCommands[cmd];
	}
	
	public String getOperatoer(int oprID){
		String output = "SELECT * FROM operatoer WHERE opr_id = #1";
		output = output.replaceFirst(illegalString + "1", Integer.toString(oprID));
		return output;
	}
	
	public String createOperatoer(OperatoerDTO opr){
		String output = "INSERT INTO operatoer(opr_id, opr_navn, ini, cpr, password) VALUES ( '#1', '#2', '#3', '#4', '#5')";
		output = output.replaceFirst(illegalString + "1", Integer.toString(opr.getOprId()));
		output = output.replaceFirst(illegalString + "2", opr.getOprNavn());
		output = output.replaceFirst(illegalString + "3", opr.getIni());
		output = output.replaceFirst(illegalString + "4", opr.getCpr());
		output = output.replaceFirst(illegalString + "5", opr.getPassword());
		return output;
	}
	
	public String createRoller(OperatoerDTO opr){
		String output = "INSERT INTO roller(opr_id, admin, operatoer, farmaceut) VALUES ( '#1', '#2', '#3', '#4')";
		output = output.replaceFirst(illegalString + "1", Integer.toString(opr.getOprId()));
		output = output.replaceFirst(illegalString + "2", Boolean.toString(opr.isAdmin()));
		output = output.replaceFirst(illegalString + "3", Boolean.toString(opr.isOperatoer()));
		output = output.replaceFirst(illegalString + "4", Boolean.toString(opr.isFarmaceut()));
		return output;
	}
	
	public String updateOperatoer(OperatoerDTO opr){
		String output = "UPDATE operatoer SET opr_navn = '#1', ini = '#2', cpr = '#3', password = '#4' WHERE opr_id = #5";
		output = output.replaceFirst(illegalString + "1", opr.getOprNavn());
		output = output.replaceFirst(illegalString + "2", opr.getIni());
		output = output.replaceFirst(illegalString + "3", opr.getCpr());
		output = output.replaceFirst(illegalString + "4", opr.getPassword());
		output = output.replaceFirst(illegalString + "5", Integer.toString(opr.getOprId()));
		return output;
	}
	
	public String getProductBatch(int pbID){
		String output = sqlCommands[5];
		output = output.replaceFirst(illegalString + "1", Integer.toString(pbID));
		return output;
	}
	
	public String createProductBatch(ProduktBatchDTO produktbatch){
		String output = sqlCommands[7];
		output = output.replaceFirst(illegalString + "1", Integer.toString(produktbatch.getPbId()));
		output = output.replaceFirst(illegalString + "2", Integer.toString(produktbatch.getStatus()));
		output = output.replaceFirst(illegalString + "3", Integer.toString(produktbatch.getReceptId()));
		return output;
	}
	
	public String updateProduktBatch(ProduktBatchDTO produktbatch){
		String output = sqlCommands[8];
		output = output.replaceFirst(illegalString + "1", Integer.toString(produktbatch.getStatus()));
		output = output.replaceFirst(illegalString + "2", Integer.toString(produktbatch.getReceptId()));
		output = output.replaceFirst(illegalString + "3", Integer.toString(produktbatch.getPbId()));
		return output;
	}
	
	public String getProduktBatchKomp(int pbId, int rbId){
		String output = sqlCommands[9];
		output = output.replaceFirst(illegalString + "1", Integer.toString(pbId));
		output = output.replaceFirst(illegalString + "2", Integer.toString(rbId));
		return output;
		}
	
	public String getProduktBatchKompList(int pbId){
		String output = sqlCommands[10];
		output = output.replaceFirst(illegalString + "1", Integer.toString(pbId));
		return output;
	}
	
	public String createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent){
		String output = sqlCommands[12];
		output = output.replaceFirst(illegalString + "1", Integer.toString(produktbatchkomponent.getPbId()));
		output = output.replaceFirst(illegalString + "2", Integer.toString(produktbatchkomponent.getRbId()));
		output = output.replaceFirst(illegalString + "3", Double.toString(produktbatchkomponent.getTara()));
		output = output.replaceFirst(illegalString + "4", Double.toString(produktbatchkomponent.getNetto()));
		output = output.replaceFirst(illegalString + "5", Integer.toString(produktbatchkomponent.getOprId()));
		return output;
	}
	
	public String updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent){
		String output = sqlCommands[13];
		output = output.replaceFirst(illegalString + "1", Double.toString(produktbatchkomponent.getTara()));
		output = output.replaceFirst(illegalString + "2", Double.toString(produktbatchkomponent.getNetto()));
		output = output.replaceFirst(illegalString + "3", Integer.toString(produktbatchkomponent.getOprId()));
		output = output.replaceFirst(illegalString + "4", Integer.toString(produktbatchkomponent.getPbId()));
		output = output.replaceFirst(illegalString + "5", Integer.toString(produktbatchkomponent.getRbId()));
		return output;
	}
	
	public String getRecept(int receptId){
		String output = sqlCommands[14];
		output = output.replaceFirst(illegalString + "1", Integer.toString(receptId));
		return output;
	}
	
	public String createRecept(ReceptDTO recept){
		String output = sqlCommands[16];
		output = output.replaceFirst(illegalString + "1", Integer.toString(recept.getReceptId()));
		output = output.replaceFirst(illegalString + "2", recept.getReceptNavn());
		return output;
	}
	
	public String updateRecept(ReceptDTO recept){
		String output = sqlCommands[17];
		output = output.replaceFirst(illegalString + "1", recept.getReceptNavn());
		output = output.replaceFirst(illegalString + "2", Integer.toString(recept.getReceptId()));
		return output;
	}
	
	public String getReceptKomp(int receptId, int raavareId){
		String output = sqlCommands[18];
		output = output.replaceFirst(illegalString + "1", Integer.toString(receptId));
		output = output.replaceFirst(illegalString + "2", Integer.toString(raavareId));
		return output;
	}
	
	public String getReceptKompList(int receptId){
		String output = sqlCommands[19];
		output = output.replaceFirst(illegalString + "1", Integer.toString(receptId));
		return output;
	}
	
	public String createReceptKomp(ReceptKompDTO receptkomponent){
		String output = sqlCommands[21];
		output = output.replaceFirst(illegalString + "1", Integer.toString(receptkomponent.getReceptId()));
		output = output.replaceFirst(illegalString + "2", Integer.toString(receptkomponent.getRaavareId()));
		output = output.replaceFirst(illegalString + "3", Double.toString(receptkomponent.getNomNetto()));
		output = output.replaceFirst(illegalString + "4", Double.toString(receptkomponent.getTolerance()));
		return output;
	}
	
	public String updateReceptKomp(ReceptKompDTO receptkomponent){
		String output = sqlCommands[22];
		output = output.replaceFirst(illegalString + "1", Double.toString(receptkomponent.getNomNetto()));
		output = output.replaceFirst(illegalString + "2", Double.toString(receptkomponent.getTolerance()));
		output = output.replaceFirst(illegalString + "3", Integer.toString(receptkomponent.getReceptId()));
		output = output.replaceFirst(illegalString + "4", Integer.toString(receptkomponent.getRaavareId()));
		return output;
	}
	
	public String getRaavareBatch(int rbId){
		String output = sqlCommands[23];
		output = output.replaceFirst(illegalString + "1", Integer.toString(rbId));
		return output;
	}
	
	public String getRaavareBatchList(int raavareId){
		String output = sqlCommands[25];
		output = output.replaceFirst(illegalString + "1", Integer.toString(raavareId));
		return output;
	}
	
	public String createRaavareBatch(RaavareBatchDTO raavarebatch){
		String output = sqlCommands[26];
		output = output.replaceFirst(illegalString + "1", Integer.toString(raavarebatch.getRbId()));
		output = output.replaceFirst(illegalString + "2", Integer.toString(raavarebatch.getRaavareId()));
		output = output.replaceFirst(illegalString + "3", Double.toString(raavarebatch.getMaengde()));
		return output;
	}
	
	public String updateRaavareBatch(RaavareBatchDTO raavarebatch){
		String output = sqlCommands[27];
		output = output.replaceFirst(illegalString + "1", Integer.toString(raavarebatch.getRaavareId()));
		output = output.replaceFirst(illegalString + "2", Double.toString(raavarebatch.getMaengde()));
		output = output.replaceFirst(illegalString + "3", Integer.toString(raavarebatch.getRbId()));
		return output;
	}
	
	public String getRaavare(int raavareId){
		String output = sqlCommands[28];
		output = output.replaceFirst(illegalString + "1", Integer.toString(raavareId));
		return output;
	}
	
	public String createRaavare(RaavareDTO raavare){
		String output = sqlCommands[30];
		output = output.replaceFirst(illegalString + "1", Integer.toString(raavare.getRaavareId()));
		output = output.replaceFirst(illegalString + "2", raavare.getRaavareNavn());
		output = output.replaceFirst(illegalString + "3", raavare.getLeverandoer());
		return output;
	}
	
	public String updateRaavare(RaavareDTO raavare){
		String output = sqlCommands[31];
		output = output.replaceFirst(illegalString + "1", raavare.getRaavareNavn());
		output = output.replaceFirst(illegalString + "2", raavare.getLeverandoer());
		output = output.replaceFirst(illegalString + "3", Integer.toString(raavare.getRaavareId()));
		return output;
	}
	
	public String getFunction(int id){
		String output = sqlCommands[41];
		output = output.replaceFirst(illegalString + "1", Integer.toString(id));
		return output;
	}

}
