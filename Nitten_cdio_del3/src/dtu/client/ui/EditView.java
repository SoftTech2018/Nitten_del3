package dtu.client.ui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.service.KartotekServiceClientImpl;
import dtu.shared.FieldVerifier;
import dtu.shared.OperatoerDTO;


public class EditView extends Composite {
	VerticalPanel editPanel;
	FlexTable t;
	
	private TextBox iniTxt;
	private TextBox nameTxt;
	private TextBox cprTxt;
	private CheckBox adminChk, farmChk, oprChk;
	final Anchor ok = new Anchor("ok");

	// valid fields - initially a field is valid
	boolean nameValid = true;
	boolean ageValid = true;
	boolean roleValid = true;
	boolean adminValue;
	boolean farmValue;
	boolean oprValue;
	
	int eventRowIndex;

	// V.2
	KartotekServiceClientImpl clientImpl;

	// person list
	List<OperatoerDTO> personer;

	// previous cancel anchor
	Anchor previousCancel = null;

	public EditView(KartotekServiceClientImpl clientImpl) {
		// V.1 this.iPersonDAO = iPersonDAO;
		// v.2
		this.clientImpl = clientImpl;

		editPanel = new VerticalPanel();
		initWidget(this.editPanel);

		t = new FlexTable();

		// adjust column widths
		t.getFlexCellFormatter().setWidth(0, 0, "25px");
		t.getFlexCellFormatter().setWidth(0, 1, "125px");
		t.getFlexCellFormatter().setWidth(0, 2, "50px");
		t.getFlexCellFormatter().setWidth(0, 3, "100px");
		t.getFlexCellFormatter().setWidth(0, 4, "50px");
		t.getFlexCellFormatter().setWidth(0, 5, "75px");
		t.getFlexCellFormatter().setWidth(0, 6, "65px");
		t.getFlexCellFormatter().setWidth(0, 7, "20px");
		t.getFlexCellFormatter().setWidth(0, 8, "40px");

		// style table
		t.addStyleName("FlexTable");
		t.getRowFormatter().addStyleName(0,"FlexTable-Header");

		// set headers in flextable
		t.setText(0, 0, "ID");
		t.setText(0, 1, "Navn");
		t.setText(0, 2, "Initial");
		t.setText(0, 3, "CPR");
		t.setText(0, 4, "Admin");
		t.setText(0, 5, "Farmaceut");
		t.setText(0, 6, "Operatoer");

		// V.2
		clientImpl.service.getPersons(new AsyncCallback<List<OperatoerDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl!" + caught.getMessage());
			}

			@Override
			public void onSuccess(List<OperatoerDTO> result) {
				// populate table and add delete anchor to each row
				for (int rowIndex=0; rowIndex < result.size(); rowIndex++) {
					t.setText(rowIndex+1, 0, "" + result.get(rowIndex).getOprId());
					t.setText(rowIndex+1, 1, result.get(rowIndex).getNavn());
					t.setText(rowIndex+1, 2, result.get(rowIndex).getIni());
					t.setText(rowIndex+1, 3, "" + result.get(rowIndex).getCpr());
					CheckBox admin = new CheckBox();
					admin.setEnabled(false);
					admin.setValue(result.get(rowIndex).isAdmin());
					t.setWidget(rowIndex+1, 4, admin);
					CheckBox farm = new CheckBox();
					farm.setEnabled(false);
					farm.setValue(result.get(rowIndex).isFarmaceut());
					t.setWidget(rowIndex+1, 5, farm);
					CheckBox opr = new CheckBox();
					opr.setEnabled(false);
					opr.setValue(result.get(rowIndex).isOperatoer());
					t.setWidget(rowIndex+1, 6, opr);
					Anchor edit = new Anchor("edit");
					t.setWidget(rowIndex+1, 7, edit);

					edit.addClickHandler(new EditHandler());
				}

			}

		});

		editPanel.add(t);

		// text boxes
		nameTxt = new TextBox();
		iniTxt = new TextBox();
		cprTxt = new TextBox();
		adminChk = new CheckBox();
		farmChk = new CheckBox();
		oprChk = new CheckBox();
		
		nameTxt.setWidth("105px");
		iniTxt.setWidth("30px");
		cprTxt.setWidth("80px");
		
		t.setText(0, 1, "Navn");
		t.setText(0, 2, "Initialer");
		t.setText(0, 3, "CPR");
		t.setText(0, 4, "Admin");
		t.setText(0, 5, "Farmaceut");
		t.setText(0, 6, "Operatoer");
	}

	private class EditHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			// if previous edit open - force cancel operation�
			if (previousCancel != null)
				previousCancel.fireEvent(new ClickEvent(){});

			// get rowindex where event happened
			eventRowIndex = t.getCellForEvent(event).getRowIndex();

			// populate textboxes
			nameTxt.setText(t.getText(eventRowIndex, 1));
			iniTxt.setText(t.getText(eventRowIndex, 2));
			cprTxt.setText(t.getText(eventRowIndex, 3));
			adminChk.setValue(((CheckBox) t.getWidget(eventRowIndex, 4)).getValue());
			farmChk.setValue(((CheckBox) t.getWidget(eventRowIndex, 5)).getValue());
			oprChk.setValue(((CheckBox) t.getWidget(eventRowIndex, 6)).getValue());
			
			adminValue = adminChk.getValue();
			farmValue = farmChk.getValue();
			oprValue = oprChk.getValue();

			// show text boxes for editing
			t.setWidget(eventRowIndex, 1, nameTxt);
			t.setWidget(eventRowIndex, 2, iniTxt);
			t.setWidget(eventRowIndex, 3, cprTxt);
			t.setWidget(eventRowIndex, 4, adminChk);
			t.setWidget(eventRowIndex, 5, farmChk);
			t.setWidget(eventRowIndex, 6, oprChk);

			// start editing here
			nameTxt.setFocus(true);

			// get edit anchor ref for cancel operation
			final Anchor edit = (Anchor) event.getSource();

			// get textbox contents for cancel operation
			final String ini = iniTxt.getText();
			final String name = nameTxt.getText();
			final String cpr = cprTxt.getText();
			final boolean adminVal = adminChk.getValue();
			final boolean farmVal = farmChk.getValue();
			final boolean oprVal = oprChk.getValue();

			
			ok.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					// remove inputboxes
					t.setText(eventRowIndex, 1, nameTxt.getText());
					t.setText(eventRowIndex, 2, iniTxt.getText());
					t.setText(eventRowIndex, 3, cprTxt.getText());
					CheckBox admin = new CheckBox();
					admin.setEnabled(false);
					admin.setValue(adminChk.getValue());
					t.setWidget(eventRowIndex, 4, admin);
					CheckBox farm = new CheckBox();
					farm.setEnabled(false);
					farm.setValue(farmChk.getValue());
					t.setWidget(eventRowIndex, 5, farm);
					CheckBox opr = new CheckBox();
					opr.setEnabled(false);
					opr.setValue(oprChk.getValue());
					t.setWidget(eventRowIndex, 6, opr);

					// here you will normally fetch the primary key of the row 
					// and use it for location the object to be edited

					// fill DTO with id and new values 
					OperatoerDTO personDTO = new OperatoerDTO(Integer.parseInt(t.getText(eventRowIndex, 0)), nameTxt.getText(), iniTxt.getText(), cprTxt.getText(), "02324it!", adminChk.getValue(), farmChk.getValue(), oprChk.getValue());

					// V.2
					clientImpl.service.updatePerson(personDTO, new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void result) {
							Window.alert("Personen blev opdateret!");
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Server fejl!" + caught.getMessage());
						}
					});

					// restore edit link
					t.setWidget(eventRowIndex, 7, edit);
					t.clearCell(eventRowIndex, 8);

					previousCancel = null;
				}
			});

			Anchor cancel = new Anchor("cancel");
			previousCancel = cancel;
			cancel.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					// restore original content of textboxes and rerun input validation
					nameTxt.setText(name);
					nameTxt.fireEvent(new KeyUpEvent() {}); // validation

					iniTxt.setText(ini);
					iniTxt.fireEvent(new KeyUpEvent() {});  // validation
					
					t.setText(eventRowIndex, 1, name);
					t.setText(eventRowIndex, 2, ini);
					t.setText(eventRowIndex, 3, cpr);
					CheckBox admin = new CheckBox();
					admin.setEnabled(false);
					admin.setValue(adminVal);
					t.setWidget(eventRowIndex, 4, admin);
					CheckBox farm = new CheckBox();
					farm.setEnabled(false);
					farm.setValue(farmVal);
					t.setWidget(eventRowIndex, 5, farm);
					CheckBox opr = new CheckBox();
					opr.setEnabled(false);
					opr.setValue(oprVal);
					t.setWidget(eventRowIndex, 6, opr);

					// restore edit link
					t.setWidget(eventRowIndex, 7, edit);
					t.clearCell(eventRowIndex, 8);

					previousCancel = null;
				}
			});

			adminChk.addClickHandler(new ClickHandler(){				
				@Override
				public void onClick(ClickEvent event) {
					adminValue = EditView.this.adminChk.getValue();
					
					// enable/disable ok depending on form status 
					if (nameValid&&ageValid&& (adminValue || farmValue || oprValue)){
						t.setWidget(eventRowIndex, 7, ok);
						roleValid = true;
					}
					else
						t.setText(eventRowIndex, 7, "ok");			
				}
			});
			
			farmChk.addClickHandler(new ClickHandler(){
				@Override
				public void onClick(ClickEvent event) {
					farmValue = EditView.this.farmChk.getValue();
					
					// enable/disable ok depending on form status 
					if (nameValid&&ageValid&& (adminValue || farmValue || oprValue)){
						t.setWidget(eventRowIndex, 7, ok);
						roleValid = true;
					} 
					else
						t.setText(eventRowIndex, 7, "ok");				
				}
			});
			
			oprChk.addClickHandler(new ClickHandler(){
				@Override
				public void onClick(ClickEvent event) {
					oprValue = EditView.this.oprChk.getValue();
					
					// enable/disable ok depending on form status 
					if (nameValid&&ageValid&& (adminValue || farmValue || oprValue)){
						t.setWidget(eventRowIndex, 7, ok);
						roleValid = true;
					} 
					else
						t.setText(eventRowIndex, 7, "ok");		
				}
			});
			
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

					// enable/disable ok depending on form status 
					if (nameValid&&ageValid&&roleValid)
						t.setWidget(eventRowIndex, 7, ok);
					else
						t.setText(eventRowIndex, 7, "ok");				
				}
			});

			cprTxt.addKeyUpHandler(new KeyUpHandler(){
				@Override
				public void onKeyUp(KeyUpEvent event) {
					if (!FieldVerifier.isValidCPR(cprTxt.getText())) {
						cprTxt.setStyleName("gwt-TextBox-invalidEntry");
						ageValid = false;
					}
					else {
						cprTxt.removeStyleName("gwt-TextBox-invalidEntry");
						ageValid = true;
					}

					// enable/disable ok depending on form status 
					if (nameValid&&ageValid)
						t.setWidget(eventRowIndex, 7, ok);
					else
						t.setText(eventRowIndex, 7, "ok");
				}
			});

			// showing ok and cancel widgets
			t.setWidget(eventRowIndex, 7 , ok);
			t.setWidget(eventRowIndex, 8 , cancel);		
		}
	}
}