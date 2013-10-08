package vac.adm;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Employee implements Serializable {
	private int idEmploees;
	private String name;
	private String position;
	private String telephone;
	private String tabNumber;
	private String login;
	private Boolean admin;
	
	public int getId() {
		return idEmploees;
	}
	public void setId(int idEmploees) {
		this.idEmploees = idEmploees;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhoneNumber() {
		return telephone;
	}
	public void setPhoneNumber(String telephone) {
		this.telephone = telephone;
	}
	
	public String getTabNumber() {
		return tabNumber;
	}
	public void setTabNumber(String tabNumber) {
		this.tabNumber = tabNumber;
	}
	
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	} 
}

