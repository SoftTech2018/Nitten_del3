package dtu.client.controller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

import dtu.client.service.KartotekServiceClientImpl;
import dtu.client.ui.ContentView;
import dtu.client.ui.MenuView;


public class MainView  {
	
	// reference to ContentView
	private ContentView contentView;
	private MenuView m;
	// V.1
	// reference to data layer
	// private IPersonDAO iPersonDAO;
	
	// V.2
	// reference to remote data layer
	private KartotekServiceClientImpl clientImpl;
	
	
	public MainView(KartotekServiceClientImpl clientImpl, String role) {
		
		// V.1
		// add implementation of data layer
		// iPersonDAO = new PersonDAO();
		
		// V.2
		// add server side implementation of data layer
		this.clientImpl = clientImpl;
		
		switch (role){
		case "ADMIN":
			// wrap menuView
			m = new MenuView(this);
			
			// wrap contentView
			contentView = new ContentView(clientImpl);
			break;
		case "FARMACEUT":
			break;
		case "OPERATOER":
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
	
}
