package dtu.client.ui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.controller.MainView;
import dtu.client.service.KartotekServiceClientImpl;
import dtu.shared.PersonDTO;

public class Login extends Composite {

	private Label userName;
	private Label password;
	private Label loginStatus;
	private TextBox user;
	private TextBox pass;
	private Button btn1;
	private KartotekServiceClientImpl clientImpl;

	public Login(KartotekServiceClientImpl clientImpl){
		this.clientImpl = clientImpl;

		VerticalPanel vPan = new VerticalPanel();
		initWidget(vPan);

		userName = new Label("Bruger-ID:");
		password = new Label("Password:");
		user = new TextBox();
		pass = new TextBox();
		btn1 = new Button("Ok");
		loginStatus = new Label("");

		HorizontalPanel hoz1 = new HorizontalPanel();
		HorizontalPanel hoz2 = new HorizontalPanel();
		HorizontalPanel hoz3 = new HorizontalPanel();

		btn1.addClickHandler(new BtnClickHandler());

		hoz1.add(userName);
		hoz1.add(user);
		hoz2.add(password);
		hoz2.add(pass);
		hoz3.add(btn1);
		vPan.add(hoz1);
		vPan.add(hoz2);
		vPan.add(hoz3);
		vPan.add(loginStatus);
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
			clientImpl.service.getPersons(new AsyncCallback<List<PersonDTO>>(){

				@Override
				public void onFailure(Throwable caught) {
					loginStatus.setText("FEJL! Kunne ikke kontakte serveren.");
				}

				@Override
				public void onSuccess(List<PersonDTO> result) {
					for (PersonDTO per : result){
						if (per.getNavn().equalsIgnoreCase(username)){
							userFound = true;
							if (per.getPassword().equals(password)){
								if (per.isAdmin()){
									RootPanel.get("section").clear();
									new MainView(clientImpl).run();
								} else {
									loginStatus.setText("Du er ikke admin.");
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
