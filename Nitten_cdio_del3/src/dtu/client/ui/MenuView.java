package dtu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import dtu.client.controller.MainView;

public class MenuView extends Composite {
	private VerticalPanel vPanel = new VerticalPanel();
	
	// receive reference to MainView for call back
	public MenuView(final MainView main) {
		initWidget(this.vPanel);
		
		Anchor showPersons = new Anchor("Vis personer");
		vPanel.add(showPersons);
		// call back the controller
		showPersons.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.showPersons();
			}
		});
	
		// use unicode escape sequence \u00F8 for 'ø'
		Anchor add = new Anchor("Tilf\u00F8j person");
		vPanel.add(add);
		add.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.addPerson();
			}
		});
		
		Anchor edit = new Anchor("Ret person");
		vPanel.add(edit);
		edit.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.editPersons();
			}
		});
		
		Anchor delete = new Anchor("Slet person");
		delete.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){				
				main.deletePersons();
			}
		});
		vPanel.add(delete);
	}
}
