package dtu.server.dal.dbconnection.daointerfaces;

import java.util.List;

import dtu.server.dal.dbconnection.dto.ProduktBatchDTO;

public interface IProduktBatchDAO {
	ProduktBatchDTO getProduktBatch(int pbId) throws DALException;
	List<ProduktBatchDTO> getProduktBatchList() throws DALException;
	void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException;
	void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException;
}