package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dtu.server.dal.dbconnection.connector.Connector;
import dtu.server.dal.dbconnection.daoimpl.ProduktBatchDAO;
import dtu.server.dal.dbconnection.daoimpl.TextReader;
import dtu.server.dal.dbconnection.daointerfaces.DALException;
import dtu.server.dal.dbconnection.daointerfaces.IProduktBatchDAO;
import dtu.server.dal.dbconnection.dto.ProduktBatchDTO;

public class ProduktBatchJunit {
	
	static IProduktBatchDAO produktBDAO;	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		TextReader txt = new TextReader("WAR");
		produktBDAO = new ProduktBatchDAO(txt);
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
			int pbID = produktBDAO.getProduktBatch(1).getPbId();
			assertEquals(1,pbID);
		} catch (DALException e) {e.printStackTrace();}
	}
	
	@Test
	public void opretProduktBatchTest() {
		int pbID = 0, temp = 0;
		try {
			for(ProduktBatchDTO pbDto : produktBDAO.getProduktBatchList()){
				temp = pbDto.getPbId();
				if (temp>pbID){
					pbID = temp;
				}				
			}
			pbID++;
			produktBDAO.createProduktBatch(new ProduktBatchDTO(pbID, 100+pbID, produktBDAO.getProduktBatch(temp).getReceptId()));
			assertEquals(100+pbID, produktBDAO.getProduktBatch(pbID).getStatus());
			
		} catch (DALException e) {e.printStackTrace();}		
	}
	
	@Test
	public void updateProduktBatchTest() {
		int pbID = 1;
		try {
			produktBDAO.updateProduktBatch(new ProduktBatchDTO(pbID, 500+pbID, produktBDAO.getProduktBatch(pbID).getReceptId()));
			assertEquals(500+pbID, produktBDAO.getProduktBatch(pbID).getStatus());
		} catch (DALException e) {e.printStackTrace();}		
	}

}
