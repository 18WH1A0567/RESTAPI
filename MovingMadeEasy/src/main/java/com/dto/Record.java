package com.dto;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
@Entity
public class Record  {
	
	@Id@GeneratedValue
	private int transactionId;
	private String custName;
	private String custPhone;	
	private String residentState;
	private String destinationArea;
	private String residentArea;
	private String destinationState;
	private String vehicleType;
	private double bill;
	private String email;
	private java.sql.Date date;
	private String requirements;
	private int otp;
	
	@ManyToOne
	@JoinColumn(name="managerId")
	@JsonIgnore
	private Manager manager;	
	
	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}


	

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public double getBill() {
		return bill;
	}

	public void setBill(double bill) {
		this.bill = bill;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String getResidentState() {
		return residentState;
	}

	public void setResidentState(String residentState) {
		this.residentState = residentState;
	}

	public String getDestinationArea() {
		return destinationArea;
	}

	public void setDestinationArea(String destinationArea) {
		this.destinationArea = destinationArea;
	}

	public String getResidentArea() {
		return residentArea;
	}

	public void setResidentArea(String residentArea) {
		this.residentArea = residentArea;
	}

	public String getDestinationState() {
		return destinationState;
	}

	public void setDestinationState(String destinationState) {
		this.destinationState = destinationState;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public int getOtp() {
		return otp;
	}


	public void setOtp(int otp) {
		this.otp = otp;
	}


}
