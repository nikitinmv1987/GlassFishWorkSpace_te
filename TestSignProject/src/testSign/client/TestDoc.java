package testSign.client;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TestDoc implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String firstRow;
	private String secondRow;
	private String signature;
	
	public TestDoc() {
	}
	@XmlElement
	public String getFirstRow() {
		return firstRow;
	}
	public void setFirstRow(String firstRow) {
		this.firstRow = firstRow;
	}
	@XmlElement
	public String getSecondRow() {
		return secondRow;
	}
	public void setSecondRow(String secondRow) {
		this.secondRow = secondRow;
	}
	@XmlElement
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
}
