package dtu.server.dal.dbconnection.daointerfaces;

import java.util.List;

import dtu.server.dal.dbconnection.dto.ProduktBatchKompDTO;

public interface IProduktBatchKompDAO {
	
	/**
	 * 
	 * @param pbId
	 * @param rbId
	 * @return Returnerer ProduktBatchKomponent
	 * @throws DALException
	 */
	ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException;
	
	/**
	 * 
	 * @param pbId
	 * @return Returnerer bestemt ProduktBatchKomponent liste
	 * @throws DALException
	 */
	List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException;
	
	/**
	 * 
	 * @return Returnerer ProduktBatchKomponent liste
	 * @throws DALException
	 */
	List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException;
	
	/**
	 * Opretter ProduktBatchKomponent
	 * @param produktbatchkomponent
	 * @throws DALException
	 */
	void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException;
	
	/**
	 * Opdater ProduktBatchKomponent
	 * @param produktbatchkomponent
	 * @throws DALException
	 */
	void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException;	
}

