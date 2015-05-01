package dtu.client.ui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.service.KartotekServiceClientImpl;
import dtu.shared.ReceptViewDTO;

public class BrowseRecept extends Composite {

	VerticalPanel browsePanel;
	private FlexTable t;
	private KartotekServiceClientImpl clientImpl;
	public int eventRowIndex;

	public BrowseRecept(KartotekServiceClientImpl clientImpl) {
		this.clientImpl = clientImpl;
		browsePanel = new VerticalPanel();
		initWidget(this.browsePanel);

		t = new FlexTable();
		t.getFlexCellFormatter().setWidth(0, 0, "50px");
		t.getFlexCellFormatter().setWidth(0, 1, "100px");
		t.getFlexCellFormatter().setWidth(0, 2, "150px");

		t.addStyleName("FlexTable");
		t.getRowFormatter().addStyleName(0,"FlexTable-Header");

		// set headers in flextable
		t.setText(0, 0, "Recept id");
		t.setText(0, 1, "Recept");
		t.setText(0, 2, "Ingredienser");

		clientImpl.service.getReceptView(new AsyncCallback<List<ReceptViewDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Server fejl! " + caught.getMessage());
			}

			@Override
			public void onSuccess(List<ReceptViewDTO> result) {
				for (int i=0; i < result.size(); i++) {
					t.getRowFormatter().addStyleName(i+1,"FlexTable-Cell");
					t.setText(i+1, 0, Integer.toString(result.get(i).getReceptId()));
					t.setText(i+1, 1, result.get(i).getReceptNavn());
					Anchor vis = new Anchor("Vis");
					vis.addClickHandler(new visRaavarer());
					t.setWidget(i+1, 2, vis);
				}
			}

		});

		browsePanel.add(t);
	}

	private class visRaavarer implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			eventRowIndex = t.getCellForEvent(event).getRowIndex();
			BrowseRecept.this.clientImpl.service.getReceptView(new AsyncCallback<List<ReceptViewDTO>>(){

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Fejl! " + caught.getMessage());

				}

				@Override
				public void onSuccess(List<ReceptViewDTO> result) {

					FlexTable tIng = new FlexTable();
					tIng.getFlexCellFormatter().setWidth(0, 0, "100px");
					tIng.getFlexCellFormatter().setWidth(0, 1, "50px");
					tIng.setText(0, 0, "Råvare");
					tIng.setText(0, 1, "Vægt");
					tIng.getRowFormatter().addStyleName(0,"FlexTable-Header");
					for (int p=0; p < result.get(eventRowIndex-1).getIngredienser().size(); p++){
						tIng.setText(p+1, 0, result.get(eventRowIndex-1).getIngredienser().get(p).getNavn());
						tIng.setText(p+1, 1, Double.toString(result.get(eventRowIndex-1).getIngredienser().get(p).getNetto()));
					}
					Anchor skjul = new Anchor("Skjul");
					tIng.setWidget(tIng.getRowCount()+1, 1, skjul);
					t.setWidget(eventRowIndex, 2, tIng);
					skjul.addClickHandler(new ClickHandler(){

						@Override
						public void onClick(ClickEvent event) {
							Anchor vis = new Anchor("Vis");
							vis.addClickHandler(new visRaavarer());
							t.setWidget(t.getCellForEvent(event).getRowIndex(), 2, vis);

						}

					});
				}


			});

		}
	}
}

