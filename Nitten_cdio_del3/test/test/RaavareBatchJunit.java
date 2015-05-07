package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dtu.server.dal.dbconnection.connector.Connector;
import dtu.server.dal.dbconnection.daoimpl.RaavareBatchDAO;
import dtu.server.dal.dbconnection.daoimpl.TextReader;
import dtu.server.dal.dbconnection.daointerfaces.DALException;
import dtu.server.dal.dbconnection.daointerfaces.IRaavareBatchDAO;
import dtu.server.dal.dbconnection.dto.RaavareBatchDTO;

public class RaavareBatchJunit {
	
	static IRaavareBatchDAO raavareBDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		TextReader txt = new TextReader("WAR");
		raavareBDAO = new RaavareBatchDAO(txt);		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
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
		int rbID = 1;
		try {
			double m = 10000+raavareBDAO.getRaavareBatch(rbID).getMaengde();
			raavareBDAO.updateRaavareBatch(new RaavareBatchDTO(rbID, raavareBDAO.getRaavareBatch(rbID).getRaavareId(), m));
			assertEquals(m, raavareBDAO.getRaavareBatch(rbID).getMaengde(), 0.01);
		} catch (DALException e) {e.printStackTrace();}		
	}

}
