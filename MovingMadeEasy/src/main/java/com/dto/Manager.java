package com.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
@Entity
public class Manager {
	@Id
	private String loginId;
	private String password;
	private String managerName;
	private String managerPhone;
	private String managerBranch;
	private double managerSalary;
	
	
	@OneToMany(mappedBy="manager",fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	//@JsonIgnore
	private List<Driver> drivers =new ArrayList<Driver>();
	
	@OneToMany(mappedBy="manager",fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnore
	private List<Record> records =new ArrayList<Record>();
	
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getManagerBranch() {
		return managerBranch;
	}

	public void setManagerBranch(String managerBranch) {
		this.managerBranch = managerBranch;
	}

	public double getManagerSalary() {
		return managerSalary;
	}

	public void setManagerSalary(double managerSalary) {
		this.managerSalary = managerSalary;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

	/*public List<Record> getCustomers() {
		return records;
	}*/

	public void setCustomers(List<Record> records) {
		this.records = records;
	}

	
}