package com.dto;

public class Bill {
	private double driverFees;
	private double vehicleFees;
	private double distanceFees;
	private double covidCharges;
	private double total;
	
	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public double getDriverFees() {
		return driverFees;
	}

	public void setDriverFees(double driverFees) {
		this.driverFees = driverFees;
	}

	public double getVehicleFees() {
		return vehicleFees;
	}

	public void setVehicleFees(double vehicleFees) {
		this.vehicleFees = vehicleFees;
	}

	public double getDistanceFees() {
		return distanceFees;
	}

	public void setDistanceFees(double distanceFees) {
		this.distanceFees = distanceFees;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getCovidCharges() {
		return covidCharges;
	}


	public void setCovidCharges(double covidCharges) {
		this.covidCharges = covidCharges;
	}


}
