package dtu.client.controller;

import com.google.gwt.user.client.ui.RootPanel;

import dtu.client.service.KartotekServiceClientImpl;
import dtu.client.ui.ContentView;
import dtu.client.ui.MenuView;

public class MainView  {

	private ContentView contentView;
	private MenuView m;	

	public MainView(KartotekServiceClientImpl clientImpl, String role) {

		switch (role){
		case "ADMIN":
			m = new MenuView(this);
			contentView = new ContentView(clientImpl);
			break;
		case "FARMACEUT":
			// tilføj farmaceut-menu + content
			break;
		case "OPERATOER":
			// tilføj operatør-menu + content
			break;
		}

		RootPanel.get("nav").add(m);
		RootPanel.get("section").add(contentView);
	}

	public void run() {
		// show welcome panel
		contentView.openWelcomeView();		
	}

	// Call back handlers
	public void addPerson() {
		contentView.openAddView();
	}

	public void showPersons() {
		contentView.openBrowseView();
	}

	public void editPersons() {
		contentView.openEditView();
	}

	public void deletePersons() {
		contentView.openDeleteView();
	}

	public void logout() {
		m.getVpanel().clear();
		contentView.openLogoutView();
	}

	public void showRecept() {
		contentView.openReceptView();		
	}

	public void showPBK() {
		contentView.openPBKView();		
	}
}