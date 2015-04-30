package dtu.client.ui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.controller.MainView;
import dtu.client.service.KartotekServiceClientImpl;
import dtu.shared.OperatoerDTO;

public class Login extends Composite {

	private Label userName;
	private Label password;
	private Label loginStatus;
	private TextBox user;
	private PasswordTextBox pass;
	private Button btn1;
	private KartotekServiceClientImpl clientImpl;

	public Login(KartotekServiceClientImpl clientImpl){
		this.clientImpl = clientImpl;

		VerticalPanel vPan = new VerticalPanel();
		initWidget(vPan);
		
		final FlexTable t = new FlexTable();
		t.getFlexCellFormatter().setWidth(0, 0, "150px");
		t.getFlexCellFormatter().setWidth(0, 1, "200px");

		userName = new Label("Bruger-ID:");
		password = new Label("Password:");
		user = new TextBox();
		pass = new PasswordTextBox();
		btn1 = new Button("Ok");
		loginStatus = new Label("");
		user.setWidth("180px");
		pass.setWidth("180px");

		btn1.addClickHandler(new BtnClickHandler());

		t.setWidget(0, 0, userName);
		t.setWidget(0, 1, user);
		t.setWidget(1, 0, password);
		t.setWidget(1, 1, pass);
		t.setWidget(2, 0, btn1);
		t.setWidget(2, 1, loginStatus);

		vPan.add(t);
		RootPanel.get("section").add(this);
	}

	private class BtnClickHandler implements ClickHandler{

		private String username, password;
		private boolean userFound;

		@Override
		public void onClick(ClickEvent event) {
			username = user.getText();
			password = pass.getText();
			userFound = false;
			clientImpl.service.getPersons(new AsyncCallback<List<OperatoerDTO>>(){

				@Override
				public void onFailure(Throwable caught) {
					loginStatus.setText("FEJL! Kunne ikke kontakte serveren.");
				}

				@Override
				public void onSuccess(List<OperatoerDTO> result) {
					for (OperatoerDTO per : result){
						if (per.getNavn().equalsIgnoreCase(username)){
							userFound = true;
							if (per.getPassword().equals(password)){
								RootPanel.get("section").clear();
								if (per.isAdmin()){
									new MainView(clientImpl, "ADMIN").run();
								} else if (per.isFarmaceut()){
									loginStatus.setText("Du er en farmaceut.");
									new MainView(clientImpl, "FARMACEUT").run();
								} else if (per.isOperatoer()){
									loginStatus.setText("Du er en operatør.");
									new MainView(clientImpl, "OPERATOER").run();
								} else {
									loginStatus.setText("Du har ikke adgang til at logge ind.");
								}
							} else {
								loginStatus.setText("Forkert password!");
							}
						}
					}
					if (!userFound){
						loginStatus.setText("Forkert bruger ID!");
					}
				}
			});
		} 			
	}
}
