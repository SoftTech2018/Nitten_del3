package dtu.server.dal.dbconnection.daointerfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dtu.shared.OperatoerDTO;

public interface IOperatoerDAO {
	public OperatoerDTO getOperatoer(int oprId) throws DALException;
	public List<OperatoerDTO> getOperatoerList() throws DALException;
	public List<OperatoerDTO> getListViewOpr() throws DALException;
	void createOperatoer(OperatoerDTO opr, int nummer) throws DALException;
	public int getOprNummer() throws DALException, SQLException;
	public void updateOprNummer() throws DALException, SQLException;
	void updateOperatoer(OperatoerDTO opr) throws DALException;
	public ResultSet getView() throws DALException;
	public void callProcedure() throws DALException;
	public void setFunction() throws DALException;
	public String getFunction(int id) throws DALException, SQLException;
	public void dropAll() throws DALException;
	
}
