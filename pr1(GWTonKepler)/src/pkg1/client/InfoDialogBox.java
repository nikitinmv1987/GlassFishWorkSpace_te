package pkg1.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class InfoDialogBox extends DialogBox {
	public VerticalPanel dialogVPanel;
	public Label lbl;
	public Button btnClose;
	public VerticalPanel rootPanel;
	
	public InfoDialogBox getDialog() {
		return this;
	}
	
	public InfoDialogBox(String caption, String text) {
		setAnimationEnabled(true);
		rootPanel = new VerticalPanel();
		rootPanel.addStyleName("dialogVPanel");
		dialogVPanel = new VerticalPanel();
		lbl = new Label(text);
		btnClose= new Button("Close");
		btnClose.getElement().setId("closeButton");
		btnClose.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				getDialog().hide();
			}
		});	
		
		setText(caption);
		dialogVPanel.add(lbl);						
		rootPanel.add(dialogVPanel);
		rootPanel.add(btnClose);
		setWidget(rootPanel);
	}

}
