package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.db.HibernateTemplate;
//import com.dto.Record;
import com.dto.Driver;
import com.dto.Manager;



public class ManagerDAO {
	
	private SessionFactory factory = null;
	
	public int register(Manager manager) {		
		return HibernateTemplate.addObject(manager);
	}
	
	public int deleteManager(String loginId){
		return HibernateTemplate.deleteObject(Manager.class, loginId);
	}
	
	public List<Manager> getAllManagers() {
		List<Manager> managers=(List)HibernateTemplate.getObjectListByQuery("From Manager");
		System.out.println("Inside All managers ..."+managers);
		return managers;	
	}
	
	public int updateDriverDetails(Driver driver){
		int result = HibernateTemplate.updateObject(driver);
		return result;
	}
	
	public Manager getManager(String loginId){
		Manager manager = (Manager)HibernateTemplate.getObject(Manager.class, loginId);
		return manager;
	}
	
	public Manager getManager(String loginId, String password){
		Manager manager = HibernateTemplate.getManagerById(loginId, password);
		return manager;
	}
	
	public Manager getManagerByBranch(String managerBranch){
		Manager manager = HibernateTemplate.getManager(managerBranch);
		return manager;
	}
	
	
	
	
	
}

/*
 * 
	
	public List<Manager> getAllManagersColumns() {
		
		String query = "select managerId, managerBranch,managerName, managerPhone, managerSalary  from Manager";
		List<Manager> managers = (List)HibernateTemplate.getObjectColumns(query);
		return managers;
		
	}

*/
