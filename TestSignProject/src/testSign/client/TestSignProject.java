package testSign.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextArea;

public class TestSignProject implements EntryPoint {
	public void onModuleLoad() {		
		RootPanel rootPanel = RootPanel.get("inputDataContainer");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		rootPanel.add(verticalPanel);
		verticalPanel.setSize("248px", "266px");
		
		final TextBox stringData1 = new TextBox();
		verticalPanel.add(stringData1);
		stringData1.setText("first row");
		stringData1.getElement().setAttribute("name", "FirstRow");
		//stringData1.getElement().setAttribute("id", "FirstRow");
		
		final TextBox stringData2 = new TextBox();
		verticalPanel.add(stringData2);
		stringData2.setText("second row");
		stringData2.getElement().setAttribute("name", "SecondRow");
		//stringData2.getElement().setAttribute("id", "SecondRow");
		
		TextArea resultText = new TextArea();
		verticalPanel.add(resultText);
		resultText.setSize("400px", "300px");
		
		resultText.getElement().setAttribute("name", "Signature");
		resultText.getElement().setAttribute("id", "Signature");
		

	}
}
