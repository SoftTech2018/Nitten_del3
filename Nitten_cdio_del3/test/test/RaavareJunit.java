package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import connector.Connector;
import daoimpl.RaavareDAO;
import daointerfaces.DALException;
import daointerfaces.IRaavareDAO;
import dto.RaavareDTO;

public class RaavareJunit {
	
	IRaavareDAO raavareDAO;

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
		
		raavareDAO = new RaavareDAO();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getOperatoerTest() {
		try {
			int rID = raavareDAO.getRaavare(1).getRaavareId();
			assertEquals(1,rID);
		} catch (DALException e) {e.printStackTrace();}		
	}
	
	@Test
	public void opretRaavareTest() {
		int rID = 0, temp = 0;
		try {
			for(RaavareDTO rDto : raavareDAO.getRaavareList()){
				temp = rDto.getRaavareId();
				if (temp>rID){
					rID = temp;	
				}				
			}
			rID++;
			String test = "test"+rID;
			raavareDAO.createRaavare(new RaavareDTO(rID, test, test));;
			assertEquals(test, raavareDAO.getRaavare(rID).getRaavareNavn());
		} catch (DALException e) {e.printStackTrace();}		
	}
	
	@Test
	public void updateRaavareTest() {
		int rID = 0;
		try {
			for(RaavareDTO rDto : raavareDAO.getRaavareList()){
				if (rDto.getRaavareNavn().startsWith("test")){
					rID = rDto.getRaavareId();
					break;
				}				
			}
			String update = "update "+raavareDAO.getRaavare(rID).getRaavareNavn();
			raavareDAO.updateRaavare(new RaavareDTO(rID, update, update));
			assertEquals(update, raavareDAO.getRaavare(rID).getRaavareNavn());
		} catch (DALException e) {e.printStackTrace();}		
	}

}
