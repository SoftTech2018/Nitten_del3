package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import connector.Connector;
import daoimpl.ProduktBatchKompDAO;
import daointerfaces.DALException;
import daointerfaces.IProduktBatchKompDAO;
import dto.ProduktBatchDTO;
import dto.ProduktBatchKompDTO;

public class ProduktBatchKompJunit {
	
	IProduktBatchKompDAO produktBKDAO;

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
		
		produktBKDAO = new ProduktBatchKompDAO();
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
	
	@Test
	public void updateProduktBatchKompTest() {
		int pbID = 0, rbID = 0;
		try {
			for(ProduktBatchKompDTO pbkDto : produktBKDAO.getProduktBatchKompList()){
				if (String.valueOf(pbkDto.getTara()).startsWith("10")){
					pbID = pbkDto.getPbId();
					rbID = pbkDto.getRbId();
					break;
				}				
			}
			double tara = produktBKDAO.getProduktBatchKomp(pbID, rbID).getTara();
			produktBKDAO.updateProduktBatchKomp(new ProduktBatchKompDTO(pbID, rbID, tara+400,produktBKDAO.getProduktBatchKomp(pbID, rbID).getNetto(), produktBKDAO.getProduktBatchKomp(pbID, rbID).getOprId()));
			assertEquals(400+tara, produktBKDAO.getProduktBatchKomp(pbID, rbID).getTara(), 0.01);
		} catch (DALException e) {e.printStackTrace();}		
	}

}
