package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dtu.server.dal.dbconnection.connector.Connector;
import dtu.server.dal.dbconnection.daoimpl.ProduktBatchKompDAO;
import dtu.server.dal.dbconnection.daoimpl.TextReader;
import dtu.server.dal.dbconnection.daointerfaces.DALException;
import dtu.server.dal.dbconnection.daointerfaces.IProduktBatchKompDAO;
import dtu.server.dal.dbconnection.dto.ProduktBatchKompDTO;



public class ProduktBatchKompJunit {
	
	static IProduktBatchKompDAO produktBKDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		TextReader txt = new TextReader("WAR");
		produktBKDAO = new ProduktBatchKompDAO(txt);
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
	public void getProduktBatchKompTest() {
		try {
			int pbID = produktBKDAO.getProduktBatchKomp(1,1).getPbId();
			int rbID = produktBKDAO.getProduktBatchKomp(1,1).getRbId();
			assertEquals(1,pbID);
			assertEquals(1,rbID);
		} catch (DALException e) {e.printStackTrace();}		
	}
	
	@Test
	public void opretProduktBatchTest() {
		int pbID = 0, rbID = 0, tempPB = 0, tempRB = 0;
		try {
			for(ProduktBatchKompDTO pbkDto : produktBKDAO.getProduktBatchKompList()){
				tempPB = pbkDto.getPbId();
				tempRB = pbkDto.getRbId();
				if (tempPB>pbID){
					pbID = tempPB;
				}
				if (tempRB>pbID){
					rbID = tempRB;
				}
			}
			pbID++;
			double tara = pbID+100.99;
			double netto = rbID+100.99;
			produktBKDAO.createProduktBatchKomp(new ProduktBatchKompDTO(pbID, rbID, tara, netto, 1));
			assertEquals(tara, produktBKDAO.getProduktBatchKomp(pbID, rbID).getTara(), 0.01);
			assertEquals(netto, produktBKDAO.getProduktBatchKomp(pbID, rbID).getNetto(), 0.01);
		} catch (DALException e) {e.printStackTrace();}		
	}
	
}
