package dtu.server.dal;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dtu.client.service.KartotekService;
import dtu.shared.PersonDTO;

public class PersonDAO extends RemoteServiceServlet implements KartotekService  {

	// primary key, autoincrement - not safe!
	private static int id = 0;

	private List<PersonDTO> pList;

	public PersonDAO() throws Exception {
		pList = new ArrayList<PersonDTO>();
		
		// Indset start data
		savePerson(new PersonDTO(id++, "Hans Jensen", "HJ", "012345-6789", "02324it!", true, true, true));
		savePerson(new PersonDTO(id++, "Ulla Jacobsen","UJ", "012345-6789", "02324it!", false, true, true));
		savePerson(new PersonDTO(id++, "Peter Hansen", "PH", "012345-6789", "02324it!", false, true, false));
	}

	@Override
	public void savePerson(PersonDTO p) throws Exception {
		// simulate server error
		// throw new RuntimeException(" \"savePerson\" fejlede");

		// add primary key
//		p.setId(id++);
		pList.add(p);
	}

	@Override
	public void updatePerson(PersonDTO p) throws Exception {
		// find object with id and update it
		for (int i=0; i<pList.size();i++)
			if (pList.get(i).getOprId() == p.getOprId())	
				pList.set(i, p);

	}


	@Override
	public List<PersonDTO> getPersons() throws Exception {
		return pList;
	}

	@Override
	public int getSize() throws Exception {
		return pList.size();
	}

	@Override
	public void deletePerson(int id) throws Exception {

		// find object with id and remove it
		for (int i=0; i<pList.size();i++)
			if (pList.get(i).getOprId() == id)	
				pList.remove(i);
	}
}
