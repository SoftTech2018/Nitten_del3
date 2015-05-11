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
	
	public OperatoerDAO(TextReader txt) throws FileNotFoundException, DALException{
		this.txt = txt;
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
		ResultSet rs = Connector.doQuery(txt.getCommand(4));
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
		ResultSet rs = Connector.doQuery(txt.getCommand(43));
		try
		{
			while (rs.next()) 
			{
				list.add(new OperatoerDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), null, null, rs.getBoolean("admin"), rs.getBoolean("operatoer"), rs.getBoolean("farmaceut")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}
	
	public ResultSet getView() throws DALException {
		return Connector.doQuery(txt.getCommand(49));
		
	}
	
	public void setProcedure() throws DALException{
		Connector.doUpdate(txt.getCommand(45));
		Connector.doUpdate(txt.getCommand(46));
	}
	
	public void callProcedure() throws DALException{
		Connector.doUpdate(txt.getCommand(47));
	}
	
	public void setFunction() throws DALException{
		Connector.doUpdate(txt.getCommand(48));
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
	
