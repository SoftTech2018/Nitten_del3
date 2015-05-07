package test;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dtu.server.dal.dbconnection.connector.Connector;
import dtu.server.dal.dbconnection.daoimpl.OperatoerDAO;
import dtu.server.dal.dbconnection.daoimpl.TextReader;
import dtu.server.dal.dbconnection.daointerfaces.IOperatoerDAO;
import dtu.shared.OperatoerDTO;

public class OperatoerJunit {
	
	static IOperatoerDAO oprDAO;
		
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }		
		TextReader txt = new TextReader("WAR");
		oprDAO = new OperatoerDAO(txt);
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
			int oprID = oprDAO.getOperatoer(1).getOprId();
			assertEquals(1,oprID);
		} catch (dtu.server.dal.dbconnection.daointerfaces.DALException e) {e.printStackTrace();}
	}
	
	@Test
	public void opretOperatoerTest() {
		int nextOprID = 0, temp = 0;
		try {
			for(OperatoerDTO oprDto : oprDAO.getOperatoerList()){
				temp = oprDto.getOprId();
				if (temp>nextOprID){
					nextOprID = temp;
				}				
			}
			nextOprID++;
			oprDAO.createOperatoer(new OperatoerDTO(0, "test"+nextOprID, "test"+nextOprID, "test"+nextOprID, "test"+nextOprID, true, false, false));
			assertEquals("test"+nextOprID, oprDAO.getOperatoer(nextOprID).getNavn());
		} catch (dtu.server.dal.dbconnection.daointerfaces.DALException e) {e.printStackTrace();}		
	}
	
	@Test
	public void updateOperatoerTest() {
		int oprID = 2;
		try {
			oprDAO.updateOperatoer(new OperatoerDTO(oprID, "update test"+oprID, "update test"+oprID, "update test"+oprID, "update test"+oprID, true, false, false));
			assertEquals("update test"+oprID, oprDAO.getOperatoer(oprID).getNavn());
		} catch (dtu.server.dal.dbconnection.daointerfaces.DALException e) {e.printStackTrace();}		
	}
}
