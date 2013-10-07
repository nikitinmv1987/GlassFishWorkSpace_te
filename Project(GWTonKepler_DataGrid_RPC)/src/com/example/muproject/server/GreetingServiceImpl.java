package com.example.muproject.server;

import java.util.ArrayList;
import java.util.List;

import com.example.muproject.client.GreetingService;
import com.example.muproject.client.Person;
import com.example.muproject.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	private final List<Person> people = new ArrayList<Person>();
	
	public Person[] getPeople() {
		people.add(new Person("Max", "Nikitin"));
		people.add(new Person("Ivan", "Ivanov"));
		people.add(new Person("Petr", "Petrov"));				
		
		Person[] results = new Person[3];
		
	    /*
	    for (int i = 0; i < 3; ++i) {
	      results[i] = people.get(i);
	    }
	    */
		
		results[0] = new Person("Max1", "Nikitin1");
		results[1] = new Person("Ivan1", "Ivanov1");
		results[2] = new Person("Petr1", "Petrov1");
		
		//Person[] results = new Person[3];
		//results[0] = people.get(0);		
		return results;
	}
}



