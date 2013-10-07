package models;

import java.io.Serializable;
import java.lang.String;

public class Person implements Serializable {

	private static final long serialVersionUID = -5237179491387289173L;
	private String firstName;
	private String lastName;

	public Person() {
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
