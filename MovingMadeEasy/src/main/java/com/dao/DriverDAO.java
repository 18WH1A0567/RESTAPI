package com.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.db.HibernateTemplate;
import com.dto.Record;
import com.dto.Driver;

public class DriverDAO {
	
private SessionFactory factory = null;
	
	public int register(Driver driver) {		
		return HibernateTemplate.addObject(driver);
	}
	
	public int deleteDriver(int vehicleId){
		return HibernateTemplate.deleteObject(Driver.class, vehicleId);
	}
	
	public List<Driver> getAllDrivers() {
		List<Driver> drivers = (List)HibernateTemplate.getObjectListByQuery("From Driver");
		System.out.println("Inside All Drivers ..."+drivers);
		return drivers;	
	}
	
	//BRANCH WISE
	public List<Driver> getAllDrivers(String branch){
		List<Driver> drivers = (List)HibernateTemplate.getObjectListByName(Driver.class, "driverBranch", branch);
		System.out.println(drivers);
		return drivers;
		
	}	
	
	public List<Driver> getAllDriversToAllocate(String branch){
		List<Driver> drivers = (List)HibernateTemplate.getAllDriversToAllocate(branch);
		return drivers;
		
	}
	
	public List<Driver> getAllDriversToDeallocate(String branch){
		List<Driver> drivers = (List)HibernateTemplate.getAllDriversToDeallocate(branch);
		return drivers;
		
	}
	
	public Driver getDriver(int vehicleId){
		Driver driver = (Driver)HibernateTemplate.getObject(Driver.class, vehicleId);
		return driver;
	}
	
	
	public int updateRecord(Driver driver){
		return HibernateTemplate.updateObject(driver);
	}
	
}

/*
 * public List<Driver> getAllDriversColumns() {
		
		String query = "select driverId,driverName,driverBranch,driverStatus,driverPhone from Driver";
		List<Driver> drivers = (List)HibernateTemplate.getObjectColumns(query);
		return drivers;
		
	}
	*/
