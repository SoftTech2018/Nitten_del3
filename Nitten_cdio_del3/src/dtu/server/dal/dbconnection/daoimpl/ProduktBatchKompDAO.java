package dtu.server.dal.dbconnection.daoimpl;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dtu.server.dal.dbconnection.connector.Connector;
import dtu.server.dal.dbconnection.daointerfaces.DALException;
import dtu.server.dal.dbconnection.daointerfaces.IProduktBatchKompDAO;
import dtu.server.dal.dbconnection.dto.ProduktBatchKompDTO;

public class ProduktBatchKompDAO implements IProduktBatchKompDAO {
	
	private TextReader txt;
	
	public ProduktBatchKompDAO(TextReader txt) throws FileNotFoundException{
		this.txt = txt;
	}

	@Override
	public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException {
		ResultSet rs = Connector.doQuery(txt.getProduktBatchKomp(pbId, rbId));		
	    try {
	    	if (!rs.first()) throw new DALException("ProduktBatchKomponent " + pbId + ", " + rbId + " findes ikke");
	    	return new ProduktBatchKompDTO (rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"),rs.getDouble("netto"),rs.getInt("opr_id"));
	    }
	    catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException {
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs = Connector.doQuery(txt.getProduktBatchKompList(pbId));
		try {
			while (rs.next()) {
				list.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"),rs.getDouble("netto"),rs.getInt("opr_id")));
			}
		} catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException {
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs = Connector.doQuery(txt.getCommand(11));
		try {
			while (rs.next()) {
				list.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"),rs.getDouble("netto"),rs.getInt("opr_id")));
			}
		} catch (SQLException e) { throw new DALException(e); }
		return list;
	}

	@Override
	public void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		Connector.doUpdate(txt.createProduktBatchKomp(produktbatchkomponent));
	}

	@Override
	public void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		Connector.doUpdate(txt.updateProduktBatchKomp(produktbatchkomponent));
	}
}
