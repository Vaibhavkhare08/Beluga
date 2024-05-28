package com.beluga.belugaproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Consignment")
public class Consignment {
	
	public Consignment() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Serial")
	private long serial;
	
	@Column(name = "Consignment Number")
	private String conNumber;
	
	@Column(name = "Reference Number")
	private String refNumber;
	
	public  Consignment(long serial, String conNumber, String refNumber) {
		this.serial = serial;
		this.conNumber = conNumber;
		this.refNumber = refNumber;
	}
	public long getSerial() {
		return serial;
	}
	public void setSerial(long serial) {
		this.serial = serial;
	}
	public String getConNumber() {
		return conNumber;
	}
	public void setConNumber(String conNumber) {
		this.conNumber = conNumber;
	}
	public String getRefNumber() {
		return refNumber;
	}
	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}
	@Override
	public String toString() {
		return "Consignment [serial=" + serial + ", conNumber=" + conNumber + ", refNumber=" + refNumber + "]";
	}
	
	
}
