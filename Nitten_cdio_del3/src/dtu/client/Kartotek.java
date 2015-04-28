package dtu.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import dtu.client.service.KartotekServiceClientImpl;
import dtu.client.ui.Login;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Kartotek implements EntryPoint {
	
	public void onModuleLoad() {
		
		KartotekServiceClientImpl clientImpl = new KartotekServiceClientImpl(GWT.getModuleBaseURL() + "kartotekservice");
		try {
			new Login(clientImpl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
