package vac.adm.models;

import java.io.Serializable;
import java.util.Date;

public class Accrual implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int idRecord;
	private int idEmployee;
	private Date date;
	private String reason;
	private double volume;
	private String note;
	
	public Accrual() {		
	}
	
	public Accrual(int idRecord, int idEmployee, Date date, String reason, double volume, String note) {		
		this.idRecord = idRecord;
		this.idEmployee = idEmployee;
		this.date = date;
		this.reason = reason;
		this.volume = volume;
		this.note = note;
	}
	
	public int getIdRecord() {
		return idRecord;
	}
	public void setIdRecord(int idRecord) {
		this.idRecord = idRecord;
	}
	
	public int getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
