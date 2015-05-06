package dtu.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.service.KartotekServiceClientImpl;



public class ContentView extends Composite {
	
	// reference to remote data layer
	private KartotekServiceClientImpl clientImpl;
	
	// reference to data layer
	//IPersonDAO iPersonDAO;

	VerticalPanel contentPanel;

	public ContentView() {
	}

	public ContentView(KartotekServiceClientImpl clientImpl) {
		this.clientImpl = clientImpl;
		contentPanel = new VerticalPanel();
		initWidget(this.contentPanel);
	}	



	// Sub views
	public void openWelcomeView() {
		contentPanel.clear();
		WelcomeView welcomeView = new WelcomeView(clientImpl);
		contentPanel.add(welcomeView);
	}

	public void openAddView() {
		contentPanel.clear();
		AddView addView = new AddView(clientImpl);
		contentPanel.add(addView);

	}

	public void openBrowseView() {
		contentPanel.clear();
		BrowseView browseView = new BrowseView(clientImpl);
		contentPanel.add(browseView);
	}

	public void openDeleteView() {
		contentPanel.clear();
		DeleteView deleteView = new DeleteView(clientImpl);
		contentPanel.add(deleteView);
	}

	public void openEditView() {
		contentPanel.clear();
		EditView editView = new EditView(clientImpl);
		contentPanel.add(editView);
	}
	
	public void openLogoutView() {
		contentPanel.clear();
		Login login = new Login(clientImpl);
		contentPanel.add(login);
	}

	public void openReceptView() {
		contentPanel.clear();
		BrowseRecept browseRecept = new BrowseRecept(clientImpl);
		contentPanel.add(browseRecept);
	}

	public void openPBKView() {
		contentPanel.clear();
		BrowseProdKomp pbk = new BrowseProdKomp(clientImpl);
		contentPanel.add(pbk);
	}


}
