package dtu.client.controller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

import dtu.client.service.KartotekServiceClientImpl;
import dtu.client.ui.ContentView;
import dtu.client.ui.MenuView;


public class MainView  {
	
	// reference to ContentView
	private ContentView contentView;
	
	// V.1
	// reference to data layer
	// private IPersonDAO iPersonDAO;
	
	// V.2
	// reference to remote data layer
	private KartotekServiceClientImpl clientImpl;
	
	
	public MainView(KartotekServiceClientImpl clientImpl) {
		
		// V.1
		// add implementation of data layer
		// iPersonDAO = new PersonDAO();
		
		// V.2
		// add server side implementation of data layer
		this.clientImpl = clientImpl;
		
		// wrap menuView
		MenuView m = new MenuView(this);
		RootPanel.get("nav").add(m);
		
		// wrap contentView
		contentView = new ContentView(clientImpl);
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
	
}
