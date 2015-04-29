package dtu.server.dal.dbconnection.daoimpl;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dtu.server.dal.dbconnection.connector.Connector;
import dtu.server.dal.dbconnection.daointerfaces.DALException;
import dtu.server.dal.dbconnection.daointerfaces.IReceptDAO;
import dtu.server.dal.dbconnection.dto.ReceptDTO;

public class ReceptDAO implements IReceptDAO {
	
	private TextReader txt;
	
	public ReceptDAO(TextReader txt) throws FileNotFoundException{
		this.txt = txt;
	}

	@Override
	public ReceptDTO getRecept(int receptId) throws DALException {
		ResultSet rs = Connector.doQuery(txt.getRecept(receptId));
	    try {
	    	if (!rs.first()) throw new DALException("Recept " + receptId + " findes ikke");
	    	return new ReceptDTO (rs.getInt("recept_id"), rs.getString("recept_navn"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ReceptDTO> getReceptList() throws DALException {
		List<ReceptDTO> list = new ArrayList<ReceptDTO>();
		ResultSet rs = Connector.doQuery(txt.getCommand(15));
		try {
			while (rs.next()) {
				list.add(new ReceptDTO(rs.getInt("recept_id"), rs.getString("recept_navn")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createRecept(ReceptDTO recept) throws DALException {
		Connector.doUpdate(txt.createRecept(recept));
	}

	@Override
	public void updateRecept(ReceptDTO recept) throws DALException {
		Connector.doUpdate(txt.updateRecept(recept));
	}

}
