package com.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.dto.Record;
import com.dto.Driver;
import com.dto.Manager;

public class HibernateTemplate {
	
	private static SessionFactory sessionFactory;
	
	static{
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public static int addObject(Object obj){		
		//System.out.println("Inside Template(add object)");
		
		int result=0;
		Transaction tx = null;
		
		try{			
			Session session=sessionFactory.openSession();
			tx=session.beginTransaction();
			System.out.println(obj);
			
			session.save(obj);
			
			tx.commit();			
			result=1;
			session.close();
		}catch (Exception e){		
			if (tx != null) {
			    tx.rollback();
			}			
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static int deleteObject(Class c,Serializable serializable){
		int result=0;		
		Session session=sessionFactory.openSession();		
		Transaction tx=session.beginTransaction();
		
		try {
			
			Object obj=session.get(c,serializable);			
			session.delete(obj);			
			tx.commit();			
			result=1; 
			session.close();
						
		} catch (Exception e) {
			
			e.printStackTrace();			
			tx.rollback();
		}		
		return result;
	}
	
	
	public static List<Object> getObjectListByName(Class c, String columName, String value) {
		
		Session session=sessionFactory.openSession();
	    Criteria criteria = session.createCriteria(c);
	    Criterion nameCriterion = Restrictions.eq(columName, value);
	    criteria.add(nameCriterion);
	    return criteria.list();
	}
	
	public static List<Object> getObjectListByQuery(String query){
		
		return sessionFactory.openSession().createQuery(query).list();
	}
	
	public static Driver getDriverByBranch(String branch) {
		
		  String queryString = "from Driver where branch = :branch";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setString("driverBranch", branch);
		  Object queryResult = query.uniqueResult();
		  Driver driver = (Driver)queryResult;
		  return driver; 
		}
	
	public static List<Driver> getAllDriversToAllocate(String branch) {
		
		  String queryString = "from Driver where driverBranch = :branch and driverStatus = 0";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setString("branch", branch);
		  List<Driver> drivers = new ArrayList<Driver>();
		  drivers = query.list();
		  return drivers;
		  
	}
	
	public static List<Driver> getAllDriversToDeallocate(String branch) {
		
		  String queryString = "from Driver where driverBranch = :branch and driverStatus = 1";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setString("branch", branch);
		  List<Driver> drivers = new ArrayList<Driver>();
		  drivers = query.list();
		  //sessionFactory.close();
		  return drivers;
		  
		}
	
	
	public static Manager getManagerById(String loginId, String password) {
		
		  String queryString = "from Manager where loginId = :loginId and password = :password";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setString("loginId", loginId);
		  query.setString("password", password);
		  Object queryResult = query.uniqueResult();
		  Manager manager = (Manager)queryResult;
		 // System.out.println(manager.getLoginId());
		  return manager; 
		}
	
	public static Manager getManager(String managerBranch){
		String queryString = "from Manager where managerBranch = :managerBranch";
		  Query query = sessionFactory.openSession().createQuery(queryString);
		  query.setString("managerBranch", managerBranch);
		  Object queryResult = query.uniqueResult();
		  Manager manager = (Manager)queryResult;
		  return manager;
	}
	
	
	public static int updateObject(Object obj){
		int result=0;		
		Transaction tx=null;
		
		try {
			
			Session session=sessionFactory.openSession();
			tx=session.beginTransaction();
			
			session.saveOrUpdate(obj);			
			tx.commit();
			
			result=1;
			session.close();
			
		} catch (Exception e) {
		
			tx.rollback();			
			e.printStackTrace();
		}		
		
		return result;
	}
	
	public static Object getObject(Class c,Serializable serializable){
		Object obj=null;
		
		try {			
			return sessionFactory.openSession().get(c,serializable);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}		
		sessionFactory.close();
		return obj;
	}
	
	public static List<Record> seeAllRequests(String branch){
		
		List<Record> records = new ArrayList<Record>();
		String queryString = "from Record where residentState like :branch and managerId is null and otp > 0";
		Query query = sessionFactory.openSession().createQuery(queryString);
		query.setString("branch", branch);
		records = query.list();	
		System.out.println(records);
		return records;
	}
	
}

/*
 * public static List<Object> getObjectColumns(String query) {
		return sessionFactory.openSession().createQuery(query).list();
		}
*/
