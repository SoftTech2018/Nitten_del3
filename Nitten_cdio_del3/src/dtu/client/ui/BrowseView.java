package dtu.client.ui;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.service.KartotekServiceClientImpl;
import dtu.shared.OperatoerDTO;

public class BrowseView extends Composite {
	VerticalPanel browsePanel;

	// reference to data layer
	// IPersonDAO iPersonDAO;

	public BrowseView(KartotekServiceClientImpl clientImpl) {
		//	this.iPersonDAO = iPersonDAO;

		browsePanel = new VerticalPanel();
		initWidget(this.browsePanel);

		final FlexTable t = new FlexTable();
		t.getFlexCellFormatter().setWidth(0, 0, "50px");
		t.getFlexCellFormatter().setWidth(0, 1, "200px");
		t.getFlexCellFormatter().setWidth(0, 2, "50px");

		t.addStyleName("FlexTable");
		t.getRowFormatter().addStyleName(0,"FlexTable-Header");
		
		// set headers in flextable
		t.setText(0, 0, "Id");
		t.setText(0, 1, "Navn");
		t.setText(0, 2, "CPR");

		// V.1
		//List<PersonDTO> personer = iPersonDAO.getPersons();

		// V.2
		clientImpl.service.getPersons(new AsyncCallback<List<OperatoerDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl!" + caught.getMessage());
			}

			@Override
			public void onSuccess(List<OperatoerDTO> result) {
				for (int i=0; i < result.size(); i++) {
					t.setText(i+1, 0, "" + result.get(i).getOprId());
					t.setText(i+1, 1, result.get(i).getNavn());
					t.setText(i+1, 2, "" + result.get(i).getCpr());
				}

			}

		});

		browsePanel.add(t);
	}
}
