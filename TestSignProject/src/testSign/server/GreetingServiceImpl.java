package testSign.server;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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
	
	private static String pojoToXml(TestDoc cust) throws JAXBException  
    {  
    	JAXBContext context = JAXBContext.newInstance(TestDoc.class);  
    	Marshaller marshaller = context.createMarshaller();  
    	marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
    	StringWriter writer = new StringWriter();  
    	marshaller.marshal(cust, writer);  
    	String xmlData = writer.toString();  
    	return xmlData;  
    }  
	
	public String pushTestDoc(TestDoc doc) throws IllegalArgumentException {
		testDoc = doc;	
		
		String s = ""; 
		
		try {
			s = pojoToXml(doc);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return s;
	}

	public TestDoc getTestDoc() {
		return testDoc;
	}

}
