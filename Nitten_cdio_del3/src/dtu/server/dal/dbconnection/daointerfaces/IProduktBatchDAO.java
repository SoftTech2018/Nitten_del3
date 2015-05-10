package dtu.server.dal.dbconnection.daointerfaces;

import java.util.List;

import dtu.server.dal.dbconnection.dto.ProduktBatchDTO;

public interface IProduktBatchDAO {
	
	/**
	 * 
	 * @param pbId
	 * @return Returnerer ProduktBatch
	 * @throws DALException
	 */
	ProduktBatchDTO getProduktBatch(int pbId) throws DALException;
	
	/**
	 * 
	 * @return Returnerer liste med ProduktBatches
	 * @throws DALException
	 */
	List<ProduktBatchDTO> getProduktBatchList() throws DALException;
	
	/**
	 * Opretter en ProduktBatch
	 * @param produktbatch
	 * @throws DALException
	 */
	void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException;
	
	/**
	 * Opdaterer en ProduktBatch
	 * @param produktbatch
	 * @throws DALException
	 */
	void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException;
}