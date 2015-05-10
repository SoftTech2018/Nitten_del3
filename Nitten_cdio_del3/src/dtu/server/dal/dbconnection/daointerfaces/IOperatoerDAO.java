package dtu.server.dal.dbconnection.daointerfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dtu.shared.OperatoerDTO;

public interface IOperatoerDAO {
	/**
	 * 
	 * @param oprId
	 * @return Returnerer et operatoer objekt
	 * @throws DALException
	 */
	public OperatoerDTO getOperatoer(int oprId) throws DALException;
	
	/**
	 * 
	 * @return Returnerer liste af operatoerer
	 * @throws DALException
	 */
	public List<OperatoerDTO> getOperatoerList() throws DALException;
	
	/**
	 * 
	 * @return Returnerer liste af operatoerer
	 * @throws DALException
	 */
	public List<OperatoerDTO> getListViewOpr() throws DALException;
	
	/**
	 * Opretter operatoer
	 * @param opr
	 * @throws DALException
	 */
	void createOperatoer(OperatoerDTO opr) throws DALException;
	
	/**
	 * Ændrer på en nuværende operatoer
	 * @param opr
	 * @throws DALException
	 */
	void updateOperatoer(OperatoerDTO opr) throws DALException;
	
	/**
	 * 
	 * @return Returnerer operatoer view
	 * @throws DALException
	 */
	public ResultSet getView() throws DALException;
	
	/**
	 * 
	 * @throws DALException
	 */
	public void callProcedure() throws DALException;
	
	
	public void setFunction() throws DALException;
	public String getFunction(int id) throws DALException, SQLException;
	public void dropAll() throws DALException;
	
}
