package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import connector.Connector;
import daoimpl.RaavareBatchDAO;
import daointerfaces.DALException;
import daointerfaces.IRaavareBatchDAO;
import dto.RaavareBatchDTO;

public class RaavareBatchJunit {
	
	IRaavareBatchDAO raavareBDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		raavareBDAO = new RaavareBatchDAO();		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getProduktBatchTest() {
		try {
			int rbID = raavareBDAO.getRaavareBatch(1).getRbId();
			assertEquals(1,rbID);
		} catch (DALException e) {e.printStackTrace();}		
	}
	
	@Test
	public void opretProduktBatchTest() {
		int rbID = 0, temp = 0;
		try {
			for(RaavareBatchDTO rbDto : raavareBDAO.getRaavareBatchList()){
				temp = rbDto.getRbId();
				if (temp>rbID){
					rbID = temp;
				}				
			}
			rbID++;
			double m = 10000+rbID;
			raavareBDAO.createRaavareBatch(new RaavareBatchDTO(rbID, raavareBDAO.getRaavareBatch(temp).getRaavareId(), m));
			assertEquals(m, raavareBDAO.getRaavareBatch(rbID).getMaengde(), 0.01);
		} catch (DALException e) {e.printStackTrace();}		
	}
	
	@Test
	public void updateRaavareBatchTest() {
		int rbID = 0;
		try {
			for(RaavareBatchDTO rbDto : raavareBDAO.getRaavareBatchList()){
				if (rbDto.getMaengde() > 10000 && String.valueOf(rbDto.getMaengde()).startsWith("100")){
					rbID = rbDto.getRbId();
					break;
				}				
			}
			double m = 10000+raavareBDAO.getRaavareBatch(rbID).getMaengde();
			raavareBDAO.updateRaavareBatch(new RaavareBatchDTO(rbID, raavareBDAO.getRaavareBatch(rbID).getRaavareId(), m));
			assertEquals(m, raavareBDAO.getRaavareBatch(rbID).getMaengde(), 0.01);
		} catch (DALException e) {e.printStackTrace();}		
	}

}
