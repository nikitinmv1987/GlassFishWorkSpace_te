package testSign.server;

import testSign.client.GreetingService;
import testSign.client.TestDoc;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	
	private TestDoc testDoc;
	
	public String pushTestDoc(TestDoc doc) throws IllegalArgumentException {
		System.out.println(doc.getFirstRow());
		System.out.println(doc.getSecondRow());
		testDoc = doc;
		
		return "Returned";
	}

	public TestDoc getTestDoc() {
		// TODO Auto-generated method stub
		return testDoc;
	}

}
