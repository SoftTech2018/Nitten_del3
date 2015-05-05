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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.service.KartotekServiceClientImpl;
import dtu.shared.FieldVerifier;
import dtu.shared.OperatoerDTO;

public class AddView extends Composite {
	
	VerticalPanel addPanel;
	FlexTable ft;

	Label nameLbl;
	Label cprLbl;
	TextBox nameTxt;
	TextBox cprTxt;
	Button save;
	CheckBox adminCB;
	CheckBox operatoerCB;
	CheckBox farmCB;

	boolean adminValue = false;
	boolean operatoerValue = false;
	boolean farmValue = false;

	public AddView(final KartotekServiceClientImpl clientImpl) {
		
		addPanel = new VerticalPanel();
		ft = new FlexTable();
		initWidget(addPanel);
		
		addPanel.add(ft);
		
		nameTxt = new TextBox();
		ft.setText(0, 0, "Navn ");
		ft.setWidget(0, 1, nameTxt);
		
		cprTxt = new TextBox();
		ft.setText(1, 0, "Cpr ");
		ft.setWidget(1, 1, cprTxt);
		
		adminCB = new CheckBox();
		ft.setText(2, 0, "Admin ");
		ft.setWidget(2, 1, adminCB);
		
		operatoerCB = new CheckBox();
		ft.setText(3, 0, "Operatør ");
		ft.setWidget(3, 1, operatoerCB);
		
		farmCB = new CheckBox();
		ft.setText(4, 0, "Farmaceut");
		ft.setWidget(4, 1, farmCB);
		
		save = new Button("Tilf\u00F8j");
		save.setEnabled(false);
		ft.setWidget(5, 0, save);
		
		save.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				OperatoerDTO newPerson = new OperatoerDTO(99, nameTxt.getText(), nameTxt.getText().substring(0, 3), cprTxt.getText(), "02324it!", adminCB.getValue(), operatoerCB.getValue(), farmCB.getValue());
				clientImpl.service.savePerson(newPerson, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						Window.alert("Person gemt i kartotek");
						nameTxt.setText("");
						cprTxt.setText("");
						adminCB.setValue(false);
						farmCB.setValue(false);
						operatoerCB.setValue(false);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Server fejl!" + caught.getMessage());
					}
				});
			}
		});
		
		nameTxt.addKeyUpHandler(new KeyUpHandler(){

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (!FieldVerifier.isValidName(nameTxt.getText())) {
					nameTxt.setStyleName("gwt-TextBox-invalidEntry");
				}
				else {
					nameTxt.removeStyleName("gwt-TextBox-invalidEntry");
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

	}

	private void checkFormValid() {
		if (FieldVerifier.isValidName(AddView.this.nameTxt.getText()) && FieldVerifier.isValidCPR(AddView.this.cprTxt.getText()) && (adminValue||operatoerValue||farmValue))
			save.setEnabled(true);
		else
			save.setEnabled(false);
	}
}