package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dtu.server.dal.dbconnection.connector.Connector;
import dtu.server.dal.dbconnection.daoimpl.ReceptDAO;
import dtu.server.dal.dbconnection.daoimpl.TextReader;
import dtu.server.dal.dbconnection.daointerfaces.DALException;
import dtu.server.dal.dbconnection.daointerfaces.IReceptDAO;
import dtu.shared.ReceptDTO;

public class ReceptJunit {
	
	static IReceptDAO receptDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		TextReader txt = new TextReader("WAR");
		receptDAO = new ReceptDAO(txt);		
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
	public void getOperatoerTest() {
		try {
			int rcpID = receptDAO.getRecept(1).getReceptId();
			assertEquals(1,rcpID);
		} catch (DALException e) {e.printStackTrace();}		
	}
	
	@Test
	public void opretReceptTest() {
		int rcpID = 0, temp = 0;
		try {
			for(ReceptDTO rcpDto : receptDAO.getReceptList()){
				temp = rcpDto.getReceptId();
				if (temp>rcpID){
					rcpID = temp;	
				}				
			}
			rcpID++;
			String test = "test"+rcpID;
			receptDAO.createRecept(new ReceptDTO(rcpID, test));
			assertEquals(test, receptDAO.getRecept(rcpID).getReceptNavn());
		} catch (DALException e) {e.printStackTrace();}		
	}
	
	@Test
	public void updateReceptTest() {
		int rcpID = 1;
		try {			
			String name = "update";
			receptDAO.updateRecept(new ReceptDTO(rcpID, name));
			assertEquals(name, receptDAO.getRecept(rcpID).getReceptNavn());
		} catch (DALException e) {e.printStackTrace();}		
	}

}
