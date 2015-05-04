package dtu.client.ui;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.service.KartotekServiceClientImpl;
import dtu.shared.FieldVerifier;
import dtu.shared.OperatoerDTO;

public class AddView extends Composite {
	VerticalPanel addPanel;

	// V.1 reference to data layer
	// IPersonDAO iPersonDAO;


	// controls
	Label nameLbl;
	Label cprLbl;
	TextBox nameTxt;
	TextBox cprTxt;
	Button save = new Button("Tilf\u00F8j");
	CheckBox adminCB;
	CheckBox operatoerCB;
	CheckBox farmCB;

	// valid fields
	boolean nameValid = false;
	boolean cprValid = false;
	boolean adminValue = false;
	boolean operatoerValue = false;
	boolean farmValue = false;

	public AddView(final KartotekServiceClientImpl clientImpl) {

		addPanel = new VerticalPanel();

		// total height of widget. Components are distributed evenly
		addPanel.setHeight("120px");	
		initWidget(this.addPanel);


		HorizontalPanel namePanel = new HorizontalPanel();
		HorizontalPanel cprPanel = new HorizontalPanel();
		HorizontalPanel adminPanel = new HorizontalPanel();
		HorizontalPanel operatoerPanel = new HorizontalPanel();
		HorizontalPanel farmaceutPanel = new HorizontalPanel();

		nameLbl = new Label("Navn:");
		nameLbl.setWidth("60px");
		nameTxt = new TextBox();
		nameTxt.setHeight("1em");
		namePanel.add(nameLbl);
		namePanel.add(nameTxt);

		cprLbl = new Label("CPR-nummer:");
		cprLbl.setWidth("60px");
		cprTxt = new TextBox();
		cprTxt.setHeight("1em");
		cprPanel.add(cprLbl);
		cprPanel.add(cprTxt);

		//admin
		Label adminLbl = new Label("Admin");
		adminLbl.setWidth("60px");
		adminCB = new CheckBox();
		adminCB.setHeight("1em");
		adminPanel.add(adminLbl);
		adminPanel.add(adminCB);

		//operatoer
		Label operatoerLbl = new Label("Operatør");
		operatoerLbl.setWidth("60px");
		operatoerCB = new CheckBox();
		operatoerCB.setHeight("1em");
		operatoerPanel.add(operatoerLbl);
		operatoerPanel.add(operatoerCB);

		//farmaceut
		Label farmLbl = new Label("Farmaceut");
		farmLbl.setWidth("60px");
		farmCB = new CheckBox();
		farmCB.setHeight("1em");
		farmaceutPanel.add(farmLbl);
		farmaceutPanel.add(farmCB);

		// use unicode escape sequence \u00F8 for '�'
		save = new Button("Tilf\u00F8j");
		save.setEnabled(false);

		save.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// v.1 
				// iPersonDAO.savePerson(new PersonDTO(nameTxt.getText(), Integer.parseInt(ageTxt.getText())));
				// Window.alert("Person gemt i kartotek");

				// V.2
				// create new PersonDTO
				OperatoerDTO newPerson = new OperatoerDTO(99, nameTxt.getText(), nameTxt.getText().substring(0, 3), cprTxt.getText(), "02324it!", adminCB.getValue(), operatoerCB.getValue(), farmCB.getValue());
				
				// save on server
				clientImpl.service.savePerson(newPerson, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						Window.alert("Person gemt i kartotek");
					}


					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Server fejl!" + caught.getMessage());
					}

				});
			}
		});


		// register event handlers
		nameTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidName(nameTxt.getText())) {
					nameTxt.setStyleName("gwt-TextBox-invalidEntry");
					nameValid = false;
				}
				else {
					nameTxt.removeStyleName("gwt-TextBox-invalidEntry");
					nameValid = true;
				}

				checkFormValid();
			}

		});

		cprTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(!FieldVerifier.isValidCPR(cprTxt.getText())){
					cprTxt.setStyleName("gwt-TextBox-invalidEntry");
				} else {
					cprTxt.removeStyleName("gwt-TextBox-invalidEntry");
					cprValid = true;
				}
				checkFormValid();
			}

		});
		
		adminCB.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				adminValue = AddView.this.adminCB.getValue();
				checkFormValid();
			}
		});
		
		operatoerCB.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				operatoerValue = AddView.this.operatoerCB.getValue();
				checkFormValid();
			}
		});
		
		farmCB.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				farmValue = AddView.this.farmCB.getValue();
				checkFormValid();
			}
			
		});
		
		addPanel.add(namePanel);
		addPanel.add(cprPanel);
		addPanel.add(adminPanel);
		addPanel.add(operatoerPanel);
		addPanel.add(farmaceutPanel);
		addPanel.add(save);
	}

	private void checkFormValid() {
		if (nameValid && cprValid && (adminValue||operatoerValue||farmValue))
			save.setEnabled(true);
		else
			save.setEnabled(false);
	}

}
