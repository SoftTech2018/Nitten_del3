package dtu.server.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dtu.client.service.KartotekService;
import dtu.shared.DALException;
import dtu.shared.OperatoerDTO;

public class PersonDAO_db extends RemoteServiceServlet implements KartotekService  {

	private static final String URL = "jdbc:mysql://localhost/kartotek";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	private Connection connection = null; // manages connection

	private PreparedStatement savePersonStmt = null;
	private PreparedStatement updatePersonStmt = null;
	private PreparedStatement getPersonsStmt = null;
	private PreparedStatement getOprViewStmt = null;
	private PreparedStatement getSizeStmt = null;
	private PreparedStatement deletePersonStmt = null;

	public PersonDAO_db() throws Exception {
		try 
		{
			connection = 
					DriverManager.getConnection( URL, USERNAME, PASSWORD );

			// create query that add a person to kartotek
			savePersonStmt = 
					connection.prepareStatement( "INSERT INTO person " + 
							"( navn, alder ) " + 
							"VALUES ( ?, ? )" );

			// create query that updates a person
			updatePersonStmt = connection.prepareStatement( 
					"UPDATE person SET navn = ?, alder = ?  WHERE id = ?" );

			// create query that get all persons in kartotek
			getPersonsStmt = connection.prepareStatement( 
					"SELECT * FROM person ");

			// create query that get all persons in kartotek
			getOprViewStmt = connection.prepareStatement( 
					"SELECT * FROM oprView ");
			
			// create query that gets size of kartotek
			getSizeStmt = connection.prepareStatement( 
					"SELECT COUNT(*) FROM person ");

			// create query that deletes a person in kartotek
			deletePersonStmt = connection.prepareStatement( 
					"DELETE FROM person WHERE id =  ? ");


		} 
		catch ( SQLException sqlException )
		{
			throw new DALException("Kan ikke oprette forbindelse til database");
		}
	}

	@Override
	public void savePerson(OperatoerDTO p) throws Exception {
		// simulate server error
		// throw new RuntimeException(" \"savePerson\" fejlede");

		try {
			savePersonStmt.setString(1, p.getNavn());
			savePersonStmt.setInt(2, Integer.valueOf(p.getCpr()));

			savePersonStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"savePerson\" fejlede");
		} 
	}

	@Override
	public void updatePerson(OperatoerDTO p) throws Exception {
		try {
			updatePersonStmt.setString(1, p.getNavn());
			updatePersonStmt.setInt(2, Integer.valueOf(p.getCpr()));
			updatePersonStmt.setInt(3, p.getOprId());

			updatePersonStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"updatePerson\" fejlede");
		} 
	}

	@Override
	public List<OperatoerDTO> getPersons() throws Exception {
		List< OperatoerDTO > results = null;
		ResultSet resultSet = null;

		try 
		{
			resultSet = getPersonsStmt.executeQuery(); 
			results = new ArrayList< OperatoerDTO >();

//			while ( resultSet.next() )
//			{
//				results.add( new PersonDTO(
//						resultSet.getInt( "id" ),
//						resultSet.getString( "navn" ),
//						resultSet.getInt( "alder" )));
//			} 
		} 
		catch ( SQLException sqlException )
		{
			throw new DALException(" \"getPersons\" fejlede");
		} 
		finally
		{
			try 
			{
				resultSet.close();
			} 
			catch ( SQLException sqlException )
			{
				sqlException.printStackTrace();         
				close();
			} 
		} 
		return results;
	} 

	@Override
	public List<OperatoerDTO> getOprView() throws Exception {
		List< OperatoerDTO > results = null;
		ResultSet resultSet = null;

		try 
		{
			resultSet = getOprViewStmt.executeQuery(); 
			results = new ArrayList< OperatoerDTO >();
		} 
		catch ( SQLException sqlException )
		{
			throw new DALException(" \"getPersons\" fejlede");
		} 
		finally
		{
			try 
			{
				resultSet.close();
			} 
			catch ( SQLException sqlException )
			{
				sqlException.printStackTrace();         
				close();
			} 
		} 
		return results;
	} 


	@Override
	public int getSize() throws Exception {
		//return size of person table
		try {
			ResultSet rs = null;
			rs = getSizeStmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new DALException(" \"getSize\" fejlede");
		} 
	}

	@Override
	public void deletePerson(int id) throws Exception {
		try {
			deletePersonStmt.setInt(1, id);

			deletePersonStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(" \"deletePerson\" fejlede");
		} 
	}

	// close the database connection
	public void close() {
		try {
			connection.close();
		} // end try
		catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} 
	}

}
