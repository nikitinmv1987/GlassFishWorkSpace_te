package vac.adm;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Employee implements Serializable {
	private int Id;
	private String Name;
	private String Position;
	private String PhoneNumber;
	private String TabNumber;
	private String Login;
	private Boolean Admin;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	
	public String getTabNumber() {
		return TabNumber;
	}
	public void setTabNumber(String tabNumber) {
		TabNumber = tabNumber;
	}
	
	public Boolean getAdmin() {
		return Admin;
	}
	public void setAdmin(Boolean admin) {
		Admin = admin;
	}
	
	public String getLogin() {
		return Login;
	}
	public void setLogin(String login) {
		Login = login;
	}
	
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	} 
}

