package dtu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dtu.shared.PersonDTO;

public interface KartotekServiceAsync {

	void savePerson(PersonDTO p, AsyncCallback<Void> callback);

	void updatePerson(PersonDTO p, AsyncCallback<Void> callback);

	void getPersons(AsyncCallback<List<PersonDTO>> callback);

	void deletePerson(int index, AsyncCallback<Void> callback);

	void getSize(AsyncCallback<Integer> callback);

}
