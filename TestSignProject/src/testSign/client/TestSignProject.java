package testSign.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.TextArea;

public class TestSignProject implements EntryPoint {
	
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	
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
		
		final TextArea resultText = new TextArea();
		verticalPanel.add(resultText);
		resultText.setSize("400px", "300px");			
		resultText.getElement().setAttribute("id", "Signature");
		//resultText.getElement().setAttribute("name", "Signature");
		
		RootPanel rootPanel2 = RootPanel.get("serverContainer");
		VerticalPanel verticalPanel2 = new VerticalPanel();
		rootPanel2.add(verticalPanel2);
		
		Button btnSend = new Button();
		btnSend.setText("Send to Server");
		verticalPanel2.add(btnSend);
		btnSend.setWidth("150px");
		
		final TestDoc doc = new TestDoc();
		
		btnSend.addClickHandler(new ClickHandler() {		
			public void onClick(ClickEvent event) {
				doc.setFirstRow(stringData1.getText());
				doc.setSecondRow(stringData2.getText());
				doc.setSignature(resultText.getText());
				stringData1.setText("");
				stringData2.setText("");
				resultText.setText("");
				
				greetingService.pushTestDoc(doc, new AsyncCallback<String>() {
					
					public void onSuccess(String result) {						
						System.out.println("yes");
					}
					
					public void onFailure(Throwable caught) {
						System.out.println("no");
					}
				});
				
			}
		});
		
		Button btnGet = new Button();
		btnGet.setText("Get from Server");
		btnGet.setWidth("150px");
		verticalPanel2.add(btnGet);
		
		btnGet.addClickHandler(new ClickHandler() {		
			public void onClick(ClickEvent event) {								
				
				greetingService.getTestDoc(new AsyncCallback<TestDoc>() {
									
					public void onFailure(Throwable caught) {
						System.out.println("no");
					}

					public void onSuccess(TestDoc result) {
						System.out.println("yes");
						stringData1.setText(result.getFirstRow());
						stringData2.setText(result.getSecondRow());
						resultText.setText(result.getSignature());
					}
				});
				
			}
		});
		
	}
}
