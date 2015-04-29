package dtu.server.dal;

import java.sql.SQLException;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dtu.client.service.KartotekService;
import dtu.server.dal.dbconnection.connector.Connector;
import dtu.server.dal.dbconnection.daoimpl.OperatoerDAO;
import dtu.server.dal.dbconnection.daoimpl.TextReader;
import dtu.server.dal.dbconnection.daointerfaces.IOperatoerDAO;
import dtu.shared.OperatoerDTO;

public class PersonDAO extends RemoteServiceServlet implements KartotekService  {

	private IOperatoerDAO oprDAO;
//	private IProduktBatchDAO prodBatchDAO;
//	private IProduktBatchKompDAO prodBatchKompDAO;
//	private IReceptDAO receptDAO;
//	private IReceptKompDAO receptKompDAO;
//	private IRaavareBatchDAO raavareBatchDAO;
//	private IRaavareDAO raavareDAO;

	public PersonDAO() throws Exception {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		TextReader txt = new TextReader();
		oprDAO = new OperatoerDAO(txt);
//		prodBatchDAO = new ProduktBatchDAO(txt);
//		prodBatchKompDAO = new ProduktBatchKompDAO(txt);
//		receptDAO = new ReceptDAO(txt);
//		receptKompDAO = new ReceptKompDAO(txt);
//		raavareBatchDAO = new RaavareBatchDAO(txt);
//		raavareDAO = new RaavareDAO(txt);
		
//		int id = 10;
//		savePerson(new OperatoerDTO(id++, "Hans Jensen", "HJ", "012345-6789", "02324it!", true, true, true));
//		savePerson(new OperatoerDTO(id++, "Ulla Jacobsen","UJ", "012345-6789", "02324it!", false, true, true));
//		savePerson(new OperatoerDTO(id++, "Peter Hansen", "PH", "012345-6789", "02324it!", false, true, false));
		}

	@Override
	public void updatePerson(OperatoerDTO p) throws Exception {
		oprDAO.updateOperatoer(p);
	}


	@Override
	public List<OperatoerDTO> getPersons() throws Exception {
		return oprDAO.getOperatoerList();
	}

	@Override
	public int getSize() throws Exception {
		return oprDAO.getOperatoerList().size();
	}

	@Override
	public void deletePerson(int id) throws Exception {
		List<OperatoerDTO> pList = oprDAO.getOperatoerList();
		for (int i=0; i<pList.size();i++)
			if (pList.get(i).getOprId() == id){
				pList.get(i).setAdmin(false);
				pList.get(i).setFarmaceut(false);
				pList.get(i).setOperatoer(false);
				oprDAO.updateOperatoer(pList.get(i));
			}
	}

	@Override
	public void savePerson(OperatoerDTO p) throws Exception {
		oprDAO.createOperatoer(p);		
	}
}
