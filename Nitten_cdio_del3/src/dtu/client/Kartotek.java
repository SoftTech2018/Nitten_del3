package dtu.client;

import com.google.gwt.core.client.EntryPoint;
import dtu.client.controller.MainView;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Kartotek implements EntryPoint {
	
	public void onModuleLoad() {
		
		new MainView().run();

	}
}
