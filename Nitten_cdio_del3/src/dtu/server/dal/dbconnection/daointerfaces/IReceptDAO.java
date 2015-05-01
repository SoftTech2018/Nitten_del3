package dtu.server.dal.dbconnection.daointerfaces;

import java.util.List;

import dtu.shared.ReceptDTO;


public interface IReceptDAO {
	ReceptDTO getRecept(int receptId) throws DALException;
	List<ReceptDTO> getReceptList() throws DALException;
	void createRecept(ReceptDTO recept) throws DALException;
	void updateRecept(ReceptDTO recept) throws DALException;
}
