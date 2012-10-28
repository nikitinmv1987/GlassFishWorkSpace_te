package vac.adm.models;

import java.io.Serializable;

public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int idEmploees;
	private String name;
	private String position;
	private String telephone;
	private String tabNumber;
	private String login;
	private Boolean admin;
	
	public Employee() {
	}
	
	public Employee(int idEmploees, String name, String position, String telephone, 
			String tabNumber, String login, Boolean admin) {
		this.idEmploees = idEmploees;
		this.name = name;
		this.position = position;
		this.telephone = telephone;
		this.tabNumber = tabNumber;
		this.login = login;
		this.admin = admin == true;				
	}	

	public int getIdEmploees() {
		return idEmploees;
	}
	public void setIdEmploees(int idEmploees) {
		this.idEmploees = idEmploees;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
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

