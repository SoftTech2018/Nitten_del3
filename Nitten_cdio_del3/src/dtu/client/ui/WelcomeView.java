package dtu.client.ui;


import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.service.KartotekServiceClientImpl;


public class WelcomeView extends Composite {

	public WelcomeView(KartotekServiceClientImpl clientImpl) {

		final VerticalPanel w = new VerticalPanel();
		initWidget(w);
		w.add(new Label("Velkommen til personkartotek"));
		
		clientImpl.service.getSize(new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl!" + caught.getMessage());
			}

			@Override
			public void onSuccess(Integer result) {
				// TODO Auto-generated method stub
				w.add(new Label("Antal personer i kartotek = " + result));
				
			}
			
		});
	
	}

}
