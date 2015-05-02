package dtu.client.ui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.service.KartotekServiceClientImpl;
import dtu.shared.OperatoerDTO;

public class BrowseView extends Composite {
	VerticalPanel browsePanel;

	// reference to data layer
	// IPersonDAO iPersonDAO;

	public BrowseView(final KartotekServiceClientImpl clientImpl) {
		//	this.iPersonDAO = iPersonDAO;

		browsePanel = new VerticalPanel();
		initWidget(this.browsePanel);

		final FlexTable t1 = new FlexTable();
		t1.getFlexCellFormatter().setWidth(0, 0, "50px");
		t1.getFlexCellFormatter().setWidth(0, 1, "200px");
		t1.getFlexCellFormatter().setWidth(0, 2, "50px");
		t1.getFlexCellFormatter().setWidth(0, 3, "100px");

		t1.addStyleName("FlexTable");
		t1.getRowFormatter().addStyleName(0,"FlexTable-Header");
		
		// set headers in flextable
		t1.setText(0, 0, "Id");
		t1.setText(0, 1, "Navn");
		t1.setText(0, 2, "INI");
		t1.setText(0, 3, "CPR");
		
		final FlexTable t2 = new FlexTable();
		t2.getFlexCellFormatter().setWidth(0, 0, "50px");
		t2.getFlexCellFormatter().setWidth(0, 1, "200px");
		t2.getFlexCellFormatter().setWidth(0, 2, "50px");
		t2.getFlexCellFormatter().setWidth(0, 3, "100px");
		t2.getFlexCellFormatter().setWidth(0, 4, "50px");
		t2.getFlexCellFormatter().setWidth(0, 5, "70px");
		t2.getFlexCellFormatter().setWidth(0, 6, "70px");
		
		t2.addStyleName("FlexTable");
		t2.getRowFormatter().addStyleName(0,"FlexTable-Header");
		
		// set headers in flextable
		t2.setText(0, 0, "Id");
		t2.setText(0, 1, "Navn");
		t2.setText(0, 2, "INI");
		t2.setText(0, 3, "CPR");
		t2.setText(0, 4, "admin");
		t2.setText(0, 5, "opreratoer");
		t2.setText(0, 6, "farmaceut");
		
		final FlexTable t3 = new FlexTable();
		t3.getFlexCellFormatter().setWidth(0, 0, "50px");
		t3.getFlexCellFormatter().setWidth(0, 1, "200px");
		t3.getFlexCellFormatter().setWidth(0, 2, "50px");
		t3.getFlexCellFormatter().setWidth(0, 3, "100px");
		t3.getFlexCellFormatter().setWidth(0, 4, "50px");
		t3.getFlexCellFormatter().setWidth(0, 5, "70px");
		t3.getFlexCellFormatter().setWidth(0, 6, "70px");
		
		t3.addStyleName("FlexTable");
		t3.getRowFormatter().addStyleName(0,"FlexTable-Header");
		
		// set headers in flextable
		t3.setText(0, 0, "Id");
		t3.setText(0, 1, "Navn");
		t3.setText(0, 2, "INI");
		t3.setText(0, 3, "CPR");
		t3.setText(0, 4, "admin");
		t3.setText(0, 5, "opreratoer");
		t3.setText(0, 6, "farmaceut");
		
		final DeckPanel deckPanel = new DeckPanel();
		
		HorizontalPanel togglePanel = new HorizontalPanel();
	    togglePanel.setSpacing(10);
	    
	 // Add a normal ToggleButton
	    final ToggleButton toggleButtonDetail = new ToggleButton("Vis detaljer");
	    toggleButtonDetail.setPixelSize(100, 20);
	    toggleButtonDetail.getUpFace().setText("Vis detaljer");
	    toggleButtonDetail.getDownFace().setText("Skjul detaljer");
	    togglePanel.add(toggleButtonDetail);
	    
	    final ToggleButton toggleButtonActive = new ToggleButton("Vis inaktive");
	    toggleButtonActive.setPixelSize(100, 20);
	    toggleButtonActive.getUpFace().setText("Vis inaktive");
	    toggleButtonActive.getDownFace().setText("Skjul inaktive");
	    toggleButtonActive.setVisible(false);
	    togglePanel.add(toggleButtonActive);
	    
		// V.1
		//List<PersonDTO> personer = iPersonDAO.getPersons();

		// V.2
		clientImpl.service.getOprView(new AsyncCallback<List<OperatoerDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl!" + caught.getMessage());
			}

			@Override
			public void onSuccess(List<OperatoerDTO> result) {
				for (int i=0; i < result.size(); i++) {
					t1.setText(i+1, 0, "" + result.get(i).getOprId());
					t1.setText(i+1, 1, result.get(i).getNavn());
					t1.setText(i+1, 2, result.get(i).getIni());
					t1.setText(i+1, 3, "" + result.get(i).getCpr());
					t1.setText(i+1, 4, "");
					t1.setText(i+1, 5, "");
					t1.setText(i+1, 6, "");
				}
			}

		});
		deckPanel.add(t1);
		
		clientImpl.service.getOprView(new AsyncCallback<List<OperatoerDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl!" + caught.getMessage());
			}

			@Override
			public void onSuccess(List<OperatoerDTO> result) {
				for (int i=0; i < result.size(); i++) {
					t2.setText(i+1, 0, "" + result.get(i).getOprId());
					t2.setText(i+1, 1, result.get(i).getNavn());
					t2.setText(i+1, 2, result.get(i).getIni());
					t2.setText(i+1, 3, "" + result.get(i).getCpr());
					if(result.get(i).isAdmin()){ t2.setText(i+1, 4, ""+result.get(i).isAdmin()); 
					} else { t2.setText(i+1, 4, ""); }
					if(result.get(i).isOperatoer()){ t2.setText(i+1, 5, ""+result.get(i).isOperatoer()); 
					} else { t2.setText(i+1, 5, ""); }
					if(result.get(i).isFarmaceut()){ t2.setText(i+1, 6, ""+result.get(i).isFarmaceut()); 
					} else { t2.setText(i+1, 6, ""); }
				}
			}

		});
		deckPanel.add(t2);
		
		
		clientImpl.service.getPersons(new AsyncCallback<List<OperatoerDTO>>() {
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl!" + caught.getMessage());
			}
			
			@Override
			public void onSuccess(List<OperatoerDTO> result) {
				for (int i=0; i < result.size(); i++) {
					t3.setText(i+1, 0, "" + result.get(i).getOprId());
					t3.setText(i+1, 1, result.get(i).getNavn());
					t3.setText(i+1, 2, result.get(i).getIni());
					t3.setText(i+1, 3, "" + result.get(i).getCpr());
					if(result.get(i).isAdmin()){ t3.setText(i+1, 4, ""+result.get(i).isAdmin()); 
					} else { t3.setText(i+1, 4, ""); }
					if(result.get(i).isOperatoer()){ t3.setText(i+1, 5, ""+result.get(i).isOperatoer()); 
					} else { t3.setText(i+1, 5, ""); }
					if(result.get(i).isFarmaceut()){ t3.setText(i+1, 6, ""+result.get(i).isFarmaceut()); 
					} else { t3.setText(i+1, 6, ""); }
				}
			}
			
		});
		deckPanel.add(t3);
		
		
		
		toggleButtonDetail.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				if(toggleButtonDetail.isDown()){
					deckPanel.showWidget(1);
					toggleButtonActive.setVisible(true);
				} else {
					deckPanel.showWidget(0);
					toggleButtonActive.setVisible(false);
					toggleButtonActive.setDown(false);
				}
				
			}
		});
		
		toggleButtonActive.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				if(toggleButtonActive.isDown()){
					deckPanel.showWidget(2);
				} else {
					deckPanel.showWidget(1);
				}
				
			}
		});
		
		browsePanel.add(togglePanel);
		browsePanel.add(deckPanel);
		deckPanel.showWidget(0);
	}
}
