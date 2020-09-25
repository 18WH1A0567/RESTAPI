package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.db.HibernateTemplate;
import com.dto.Record;
import com.dto.Driver;
import com.dto.Manager;


public class RecordDAO {
	
private SessionFactory factory = null;
	
	public int register(Record record) {		
		return HibernateTemplate.addObject(record);
	}
	
	public List<Record> getAllCustomers() {
		//HibernateTemplate.deleteExtras();
		List<Record> records=(List)HibernateTemplate.getObjectListByQuery("From Record");
		System.out.println("Inside All Customers ..."+records);
		return records;	
	}
	
	public List<Record> seeAllRequests(String managerBranch){
		//String query = "from Customer where managerId is NULL and source is " + managerBranch;
		//HibernateTemplate.deleteExtras();
		List<Record> records = new ArrayList<Record>();
		records = (List)HibernateTemplate.seeAllRequests(managerBranch);
		return records;
	}

	public Record getRecord(int transactionId) {
		// TODO Auto-generated method stub
		Record record = new Record();
		record = (Record)HibernateTemplate.getObject(Record.class, transactionId);
		return record;
	}
	
	public int updateRecord(Record record){
		return HibernateTemplate.updateObject(record);
	}

	
}

/*
 * public List<Customer> getAllCustomersColumns() {
		
		String query = "select custName, custPhone from Customer";
		List<Customer> customers = (List)HibernateTemplate.getObjectColumns(query);
		return customers;
		
	}
*/
