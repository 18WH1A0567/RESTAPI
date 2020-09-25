package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.dto.Bill;
import com.dto.Record;

public class BillDAO {
	
	public List<Object> billCalculation(Record record) throws Exception{
		String place1, place2, type;
		place1 = record.getResidentArea();
		place2 = record.getDestinationArea();
		type = record.getVehicleType();
		Bill bill = new Bill();
		
		bill.setDriverFees(5000);
		bill.setCovidCharges(4000);
		
		if(type.equals("Truck")){
			bill.setVehicleFees(5000);
		}
		else{
			bill.setVehicleFees(4000);
		}
		
		DistanceDAO distanceDAO = new DistanceDAO();
		double dist = distanceDAO.findDistAndCoord(place1, place2);
		bill.setDistanceFees(dist * 12);
		System.out.println(dist);
		bill.setTotal(bill.getDistanceFees() + bill.getDriverFees() + bill.getVehicleFees() + bill.getCovidCharges());
		List<Object> list = new ArrayList<Object>();
		list.add(dist);
		list.add(bill);
		
		
		return list;
	}


}
