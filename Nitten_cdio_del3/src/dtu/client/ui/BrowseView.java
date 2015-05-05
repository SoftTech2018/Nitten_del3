package dtu.client.ui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
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

	public BrowseView(final KartotekServiceClientImpl clientImpl) {

		browsePanel = new VerticalPanel();
		initWidget(this.browsePanel);

		final FlexTable t1 = new FlexTable();
		t1.setWidth("200px");
		t1.getFlexCellFormatter().setWidth(0, 0, "25px");
		t1.getFlexCellFormatter().setWidth(0, 1, "125px");
		t1.getFlexCellFormatter().setWidth(0, 2, "50px");

		t1.addStyleName("FlexTable");
		t1.getRowFormatter().addStyleName(0,"FlexTable-Header");

		// set headers in flextable
		t1.setText(0, 0, "Id");
		t1.setText(0, 1, "Navn");
		t1.setText(0, 2, "Initial");

		final FlexTable t2 = new FlexTable();
		t2.getFlexCellFormatter().setWidth(0, 0, "25px");
		t2.getFlexCellFormatter().setWidth(0, 1, "125px");
		t2.getFlexCellFormatter().setWidth(0, 2, "50px");
		t2.getFlexCellFormatter().setWidth(0, 3, "50px");
		t2.getFlexCellFormatter().setWidth(0, 4, "70px");
		t2.getFlexCellFormatter().setWidth(0, 5, "70px");

		t2.addStyleName("FlexTable");
		t2.getRowFormatter().addStyleName(0,"FlexTable-Header");

		// set headers in flextable
		t2.setText(0, 0, "Id");
		t2.setText(0, 1, "Navn");
		t2.setText(0, 2, "Initial");
		t2.setText(0, 3, "admin");
		t2.setText(0, 4, "opreratoer");
		t2.setText(0, 5, "farmaceut");

		final FlexTable t3 = new FlexTable();
		t3.getFlexCellFormatter().setWidth(0, 0, "25px");
		t3.getFlexCellFormatter().setWidth(0, 1, "125px");
		t3.getFlexCellFormatter().setWidth(0, 2, "50px");
		t3.getFlexCellFormatter().setWidth(0, 3, "50px");
		t3.getFlexCellFormatter().setWidth(0, 4, "70px");
		t3.getFlexCellFormatter().setWidth(0, 5, "70px");

		t3.addStyleName("FlexTable");
		t3.getRowFormatter().addStyleName(0,"FlexTable-Header");

		// set headers in flextable
		t3.setText(0, 0, "Id");
		t3.setText(0, 1, "Navn");
		t3.setText(0, 2, "Initial");
		t3.setText(0, 3, "admin");
		t3.setText(0, 4, "opreratoer");
		t3.setText(0, 5, "farmaceut");

		t3.addStyleName("FlexTable");

		final FlexTable t4 = new FlexTable();
		t4.getFlexCellFormatter().setWidth(0, 0, "25px");
		t4.getFlexCellFormatter().setWidth(0, 1, "125px");
		t4.getFlexCellFormatter().setWidth(0, 2, "50px");

		t4.addStyleName("FlexTable");
		t4.getRowFormatter().addStyleName(0,"FlexTable-Header");

		// set headers in flextable
		t4.setText(0, 0, "Id");
		t4.setText(0, 1, "Navn");
		t4.setText(0, 2, "Initial");


		final DeckPanel deckPanel = new DeckPanel();
		
		deckPanel.add(t1);
		deckPanel.add(t2);
		deckPanel.add(t3);
		deckPanel.add(t4);

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

		final CheckBox checkBox = new CheckBox("Vis KUN inaktive");
		checkBox.setVisible(false);
		togglePanel.add(checkBox);


		clientImpl.service.getOprView(new AsyncCallback<List<OperatoerDTO>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl! (deck 1)" + caught.getMessage());
			}

			@Override
			public void onSuccess(List<OperatoerDTO> result) {
				for (int i=0; i < result.size(); i++) {
					t1.setText(i+1, 0, "" + result.get(i).getOprId());
					t1.setText(i+1, 1, result.get(i).getNavn());
					t1.setText(i+1, 2, result.get(i).getIni());
				}
			}
		});


		toggleButtonDetail.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				if(toggleButtonDetail.isDown()){
					clientImpl.service.getOprView(new AsyncCallback<List<OperatoerDTO>>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Server fejl! (deck 2)" + caught.getMessage());
						}

						@Override
						public void onSuccess(List<OperatoerDTO> result) {							
							for (int i=0; i < result.size(); i++) {
								t2.setText(i+1, 0, "" + result.get(i).getOprId());
								t2.setText(i+1, 1, result.get(i).getNavn());
								t2.setText(i+1, 2, result.get(i).getIni());
								if(result.get(i).isAdmin()){ t2.setText(i+1, 3, "X"); 
								} else { t2.setText(i+1, 3, "-"); }
								if(result.get(i).isOperatoer()){ t2.setText(i+1, 4, "X"); 
								} else { t2.setText(i+1, 4, "-"); }
								if(result.get(i).isFarmaceut()){ t2.setText(i+1, 5, "X"); 
								} else { t2.setText(i+1, 5, "-"); }
							}
						}
					});
					deckPanel.showWidget(1);
					toggleButtonActive.setVisible(true);
				} else {
					clientImpl.service.getOprView(new AsyncCallback<List<OperatoerDTO>>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Server fejl! (deck 1)" + caught.getMessage());
						}

						@Override
						public void onSuccess(List<OperatoerDTO> result) {
							for (int i=0; i < result.size(); i++) {
								t1.setText(i+1, 0, "" + result.get(i).getOprId());
								t1.setText(i+1, 1, result.get(i).getNavn());
								t1.setText(i+1, 2, result.get(i).getIni());
							}
						}
					});
					deckPanel.showWidget(0);
					toggleButtonActive.setVisible(false);
					toggleButtonActive.setDown(false);
					checkBox.setVisible(false);
					checkBox.setValue(false);
				}
			}
		});

		toggleButtonActive.addClickHandler(new ClickHandler() {		
			@Override
			public void onClick(ClickEvent event) {
				if(toggleButtonActive.isDown()){
					clientImpl.service.getPersons(new AsyncCallback<List<OperatoerDTO>>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Server fejl! (deck 3)" + caught.getMessage());
						}

						@Override
						public void onSuccess(List<OperatoerDTO> result) {
							for (int i=0; i < result.size(); i++) {
								t3.setText(i+1, 0, "" + result.get(i).getOprId());
								t3.setText(i+1, 1, result.get(i).getNavn());
								t3.setText(i+1, 2, result.get(i).getIni());
								if(result.get(i).isAdmin()){ t3.setText(i+1, 3, "X");
								} else { t3.setText(i+1, 3, "-"); }
								if(result.get(i).isOperatoer()){ t3.setText(i+1, 4, "X"); 
								} else { t3.setText(i+1, 4, "-"); }
								if(result.get(i).isFarmaceut()){ t3.setText(i+1, 5, "X"); 
								} else { t3.setText(i+1, 5, "-"); }
							}				
						}
					});
					deckPanel.showWidget(2);
					checkBox.setVisible(true);
				} else {
					clientImpl.service.getOprView(new AsyncCallback<List<OperatoerDTO>>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Server fejl! (deck 2)" + caught.getMessage());
						}

						@Override
						public void onSuccess(List<OperatoerDTO> result) {
							for (int i=0; i < result.size(); i++) {
								t2.setText(i+1, 0, "" + result.get(i).getOprId());
								t2.setText(i+1, 1, result.get(i).getNavn());
								t2.setText(i+1, 2, result.get(i).getIni());
								if(result.get(i).isAdmin()){ t2.setText(i+1, 3, "X"); 
								} else { t2.setText(i+1, 3, "-"); }
								if(result.get(i).isOperatoer()){ t2.setText(i+1, 4, "X"); 
								} else { t2.setText(i+1, 4, "-"); }
								if(result.get(i).isFarmaceut()){ t2.setText(i+1, 5, "X"); 
								} else { t2.setText(i+1, 5, "-"); }
							}
						}
					});
					deckPanel.showWidget(1);
					checkBox.setVisible(false);
					checkBox.setValue(false);
				}
			}
		});

		checkBox.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if(checkBox.getValue()){
					clientImpl.service.getPersons(new AsyncCallback<List<OperatoerDTO>>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Server fejl! (deck 4)" + caught.getMessage());
						}

						@Override
						public void onSuccess(List<OperatoerDTO> result) {
							int temp = 0;
							for (int i=0; i < result.size(); i++) {
								if(!result.get(i).isAdmin() && !result.get(i).isOperatoer() && !result.get(i).isFarmaceut()){
									temp++;
									t4.setText(temp, 0, "" + result.get(i).getOprId());
									t4.setText(temp, 1, result.get(i).getNavn());
									t4.setText(temp, 2, result.get(i).getIni());
								}
							}
						}
					});
					deckPanel.showWidget(3);
				} else {
					clientImpl.service.getPersons(new AsyncCallback<List<OperatoerDTO>>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Server fejl! (deck 3)" + caught.getMessage());
						}

						@Override
						public void onSuccess(List<OperatoerDTO> result) {
							for (int i=0; i < result.size(); i++) {
								t3.setText(i+1, 0, "" + result.get(i).getOprId());
								t3.setText(i+1, 1, result.get(i).getNavn());
								t3.setText(i+1, 2, result.get(i).getIni());
								if(result.get(i).isAdmin()){ t3.setText(i+1, 3, "X");
								} else { t3.setText(i+1, 3, "-"); }
								if(result.get(i).isOperatoer()){ t3.setText(i+1, 4, "X"); 
								} else { t3.setText(i+1, 4, "-"); }
								if(result.get(i).isFarmaceut()){ t3.setText(i+1, 5, "X"); 
								} else { t3.setText(i+1, 5, "-"); }
							}				
						}
					});
					deckPanel.showWidget(2);
				}
			}
		});
		browsePanel.add(togglePanel);
		browsePanel.add(deckPanel);
		deckPanel.showWidget(0);
	}
}
