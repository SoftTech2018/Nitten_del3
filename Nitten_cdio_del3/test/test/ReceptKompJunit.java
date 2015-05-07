package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dtu.server.dal.dbconnection.connector.Connector;
import dtu.server.dal.dbconnection.daoimpl.ReceptKompDAO;
import dtu.server.dal.dbconnection.daoimpl.TextReader;
import dtu.server.dal.dbconnection.daointerfaces.DALException;
import dtu.server.dal.dbconnection.daointerfaces.IReceptKompDAO;
import dtu.server.dal.dbconnection.dto.ReceptKompDTO;



public class ReceptKompJunit {
	
	static IReceptKompDAO receptKDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		TextReader txt = new TextReader("WAR");
		receptKDAO = new ReceptKompDAO(txt);
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
	public void getReceptKompTest() {
		try {
			int rcpID = receptKDAO.getReceptKomp(1,1).getReceptId();
			int ravID = receptKDAO.getReceptKomp(1,1).getRaavareId();
			assertEquals(1,rcpID);
			assertEquals(1,ravID);
		} catch (DALException e) {e.printStackTrace();}		
	}
	
	@Test
	public void opretReceptKompTest() {
		int rcpID = 3, ravID = 3;
		try {
			double nomNet = rcpID+100.99;
			double tol = ravID+100.99;
			receptKDAO.createReceptKomp(new ReceptKompDTO(rcpID, ravID, nomNet, tol));
			assertEquals(nomNet, receptKDAO.getReceptKomp(rcpID, ravID).getNomNetto(), 0.01);
			assertEquals(tol, receptKDAO.getReceptKomp(rcpID, ravID).getTolerance(), 0.01);
		} catch (DALException e) {e.printStackTrace();}
	}
	
	@Test
	public void updateReceptKompTest() {
		int rcpID = 3, ravID = 3;
		try {
			receptKDAO.updateReceptKomp(new ReceptKompDTO(rcpID, ravID, 500+rcpID, 500+ravID));
			assertEquals(500+rcpID, receptKDAO.getReceptKomp(rcpID, ravID).getNomNetto(), 0.01);
			assertEquals(500+ravID, receptKDAO.getReceptKomp(rcpID, ravID).getTolerance(), 0.01);
		} catch (DALException e) {e.printStackTrace();}		
	}
}
