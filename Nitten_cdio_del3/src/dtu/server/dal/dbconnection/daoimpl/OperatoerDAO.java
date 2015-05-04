package dtu.server.dal.dbconnection.daoimpl;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import dtu.server.dal.dbconnection.connector.Connector;
import dtu.server.dal.dbconnection.daointerfaces.DALException;
import dtu.server.dal.dbconnection.daointerfaces.IOperatoerDAO;
import dtu.shared.OperatoerDTO;

public class OperatoerDAO implements IOperatoerDAO {
	
	TextReader txt;
	private String view = "oprListView";
	
	public OperatoerDAO(TextReader txt) throws FileNotFoundException, DALException{
		this.txt = txt;
		Connector.doUpdate("CREATE OR REPLACE VIEW "+view+" AS SELECT * FROM operatoer NATURAL JOIN roller WHERE roller.admin+roller.operatoer+roller.farmaceut>0");
		this.setProcedure();
	}
	
	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		ResultSet rs = Connector.doQuery(txt.getOperatoer(oprId));
	    try {
	    	if (!rs.first()) throw new DALException("Operatoeren " + oprId + " findes ikke");
	    	return new OperatoerDTO (rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"), rs.getBoolean("admin"), rs.getBoolean("operatoer"), rs.getBoolean("farmaceut"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}
	
	public void createOperatoer(OperatoerDTO opr) throws DALException {		
			Connector.doUpdate(txt.createOperatoer(opr));
	}
	
	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		Connector.doUpdate(txt.updateOperatoer(opr));
		Connector.doUpdate(txt.updateOprRolle(opr));
	}
	
	public List<OperatoerDTO> getOperatoerList() throws DALException {
		List<OperatoerDTO> list = new ArrayList<OperatoerDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM operatoer NATURAL JOIN roller");
		try
		{
			while (rs.next()) 
			{
				list.add(new OperatoerDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"), rs.getBoolean("admin"), rs.getBoolean("operatoer"), rs.getBoolean("farmaceut")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}
	
	public List<OperatoerDTO> getListViewOpr() throws DALException {
		List<OperatoerDTO> list = new ArrayList<OperatoerDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM "+view);
		try
		{
			while (rs.next()) 
			{
				list.add(new OperatoerDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"), rs.getBoolean("admin"), rs.getBoolean("operatoer"), rs.getBoolean("farmaceut")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}
	
	public ResultSet getView() throws DALException {
		return Connector.doQuery("select * from oprView");
		
	}
	
	public void setProcedure() throws DALException{
		Connector.doUpdate("DROP PROCEDURE IF EXISTS createOPR;");
		Connector.doUpdate("CREATE PROCEDURE createOPR(oprNAVN VARCHAR(20),init VARCHAR(20),cprNR VARCHAR(20),pass VARCHAR(20),admROLE BOOLEAN,oprROLE BOOLEAN,farmROLE BOOLEAN) BEGIN DECLARE oprID INT;SELECT * INTO oprID FROM operatoernummer;INSERT INTO operatoer(opr_id,opr_navn,ini,cpr,password) VALUES(oprID,oprNAVN,init,cprNR,pass);INSERT INTO roller(opr_id,admin,operatoer,farmaceut) VALUES(oprID,admROLE,oprROLE,farmROLE);SET oprID:=oprID+1;UPDATE operatoernummer SET opr_nummer=oprID;END;");
	}
	
	public void callProcedure() throws DALException{
		Connector.doUpdate("call setView()");
	}
	
	public void setFunction() throws DALException{
		Connector.doUpdate("CREATE FUNCTION oprID(oID INT) RETURNS VARCHAR(20) BEGIN DECLARE navn VARCHAR(20); SELECT opr_navn INTO navn FROM operatoer WHERE opr_id = oID; RETURN navn; END;");
	}
	
	public String getFunction(int id) throws DALException, SQLException{
		ResultSet temp = Connector.doQuery(txt.getFunction(id));
		temp.next();
		return temp.getString(1);
	}
	
	public void dropAll() throws DALException {
		Connector.doUpdate(txt.getCommand(37));
		Connector.doUpdate(txt.getCommand(38));
		Connector.doUpdate(txt.getCommand(39));
		Connector.doUpdate(txt.getCommand(40));
	}
		
}
	
