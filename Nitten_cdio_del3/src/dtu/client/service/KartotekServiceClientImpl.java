package dtu.client.service;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
public class KartotekServiceClientImpl {
	
	// global reference to service endpoint
	public KartotekServiceAsync service;
	
	public KartotekServiceClientImpl(String url) {
		// Set RPC service end point
		this.service = GWT.create(KartotekService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
	}
}
