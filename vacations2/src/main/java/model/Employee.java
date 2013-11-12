package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the Employees database table.
 * 
 */
@Entity
@Table(name="Employees")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IdEmploees")
	private short idEmploees;

	@Column(name="Admin")
	private boolean admin;

	@Column(name="Login")
	private String login;

	@Column(name="Name")
	private String name;

	@Column(name="Position")
	private String position;

	@Column(name="TabNumber")
	private String tabNumber;

	@Column(name="Telephone")
	private String telephone;

	@OneToMany(mappedBy="employee")
	@OrderBy("date desc")
	private List<Vacation> vacations;

	public Employee() {
	}

	public short getIdEmploees() {
		return this.idEmploees;
	}

	public void setIdEmploees(short idEmploees) {
		this.idEmploees = idEmploees;
	}

	public boolean getAdmin() {
		return this.admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTabNumber() {
		return this.tabNumber;
	}

	public void setTabNumber(String tabNumber) {
		this.tabNumber = tabNumber;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<Vacation> getVacations() {
		return this.vacations;
	}

	public void setVacations(List<Vacation> vacations) {
		this.vacations = vacations;
	}

	public Vacation addVacation(Vacation vacation) {
		getVacations().add(vacation);
		vacation.setEmployee(this);

		return vacation;
	}

	public Vacation removeVacation(Vacation vacation) {
		getVacations().remove(vacation);
		vacation.setEmployee(null);

		return vacation;
	}

}