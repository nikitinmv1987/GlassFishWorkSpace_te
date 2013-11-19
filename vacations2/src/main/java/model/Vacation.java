package model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.math.BigDecimal;


/**
 * The persistent class for the Vacations database table.
 * 
 */
@Entity
@Table(name="Vacations")
@NamedQuery(name="Vacation.findAll", query="SELECT v FROM Vacation v")
public class Vacation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IdRecord")
	private int idRecord;

	@Column(name="audit_date")
	private Timestamp auditDate;

	@Column(name="audit_user")
	private String auditUser;

	@Column(name="Date")
	private Date date;

	@Column(name="Note")
	private String note;

	@Column(name="Reason")
	private String reason;

	@Column(name="Volume")
	private BigDecimal volume;
	
	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="IdEmloyee")	
	private Employee employee;

	public Vacation() {
	}

	public int getIdRecord() {
		return this.idRecord;
	}

	public void setIdRecord(int idRecord) {
		this.idRecord = idRecord;
	}

	public Timestamp getAuditDate() {
		return this.auditDate;
	}

	public void setAuditDate(Timestamp auditDate) {
		this.auditDate = auditDate;
	}

	public String getAuditUser() {
		return this.auditUser;
	}

	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public BigDecimal getVolume() {
		return this.volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}