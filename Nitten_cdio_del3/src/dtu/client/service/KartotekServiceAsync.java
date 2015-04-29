package dtu.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dtu.shared.OperatoerDTO;

public interface KartotekServiceAsync {

	void savePerson(OperatoerDTO p, AsyncCallback<Void> callback);

	void updatePerson(OperatoerDTO p, AsyncCallback<Void> callback);

	void getPersons(AsyncCallback<List<OperatoerDTO>> callback);

	void deletePerson(int index, AsyncCallback<Void> callback);

	void getSize(AsyncCallback<Integer> callback);

}
