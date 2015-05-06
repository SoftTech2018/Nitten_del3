package dtu.client.ui;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.shared.ProdBatchInfo;
import dtu.client.service.KartotekServiceClientImpl;

public class BrowseProdKomp extends Composite {

	private VerticalPanel browsePanel;
	private FlexTable t;

	public BrowseProdKomp(KartotekServiceClientImpl clientImpl) {
		browsePanel = new VerticalPanel();
		initWidget(this.browsePanel);
		t = new FlexTable();
		t.getFlexCellFormatter().setWidth(0, 0, "50px");
		t.getFlexCellFormatter().setWidth(0, 1, "75px");
		t.getFlexCellFormatter().setWidth(0, 2, "50px");
		t.getFlexCellFormatter().setWidth(0, 3, "50px");
		t.getFlexCellFormatter().setWidth(0, 4, "50px");
		t.getFlexCellFormatter().setWidth(0, 5, "75px");
		t.getFlexCellFormatter().setWidth(0, 6, "50px");

		t.addStyleName("FlexTable");
		t.getRowFormatter().addStyleName(0,"FlexTable-Header");
		t.setText(0, 0, "Recept id");
		t.setText(0, 1, "Recept navn");
		t.setText(0, 2, "Netto vægt");
		t.setText(0, 3, "PB id");
		t.setText(0, 4, "Opr id");
		t.setText(0, 5, "Opr navn");
		t.setText(0, 6, "Status");

		browsePanel.add(t);
		clientImpl.service.getProdBatchInfoView(new AsyncCallback<List<ProdBatchInfo>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fejl! " + caught.getMessage());
			}

			@Override
			public void onSuccess(List<ProdBatchInfo> result) {
				for (int i=0; i < result.size(); i++){
					t.setText(i+1, 0, Integer.toString(result.get(i).getRecept_id()));
					t.setText(i+1, 1, result.get(i).getRecept_navn());
					t.setText(i+1, 2, Double.toString(result.get(i).getNetto()));
					t.setText(i+1, 3, Integer.toString(result.get(i).getPb_id()));
					t.setText(i+1, 4, Integer.toString(result.get(i).getOpr_id()));
					t.setText(i+1, 5, result.get(i).getOpr_navn());
					t.setText(i+1, 6, Integer.toString(result.get(i).getStatus()));
				}
			}
		});
	}
}
