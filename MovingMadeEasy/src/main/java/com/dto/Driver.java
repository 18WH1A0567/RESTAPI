package com.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.type.SerializableType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement
@Entity

public class Driver  {
	private String driverName;
	private String driverPhone;
	private boolean driverStatus;
	private String driverBranch;
	private double salary;
	@Id
	private int vehicleId;
	private String vehicleType;
	private double vehicleCharges;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="managerId")
	@JsonIgnore
	private Manager manager;

	public Driver() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverPhone() {
		return driverPhone;
	}

	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}

	public boolean isDriverStatus() {
		return driverStatus;
	}

	public void setDriverStatus(boolean driverStatus) {
		this.driverStatus = driverStatus;
	}

	public String getDriverBranch() {
		return driverBranch;
	}

	public void setDriverBranch(String driverBranch) {
		this.driverBranch = driverBranch;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	public double getVehicleCharges() {
		return vehicleCharges;
	}

	public void setVehicleCharges(double vehicleCharges) {
		this.vehicleCharges = vehicleCharges;
	}

	

}