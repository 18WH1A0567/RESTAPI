package com.dao;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

	
	public class DistanceDAO {
		public static double calDistance(double lon1, double lat1, double lon2, double lat2){
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			dist = dist * 1.609344;

			return (dist);
		}
		
		public static List<Double> Coordinates(String city) throws Exception{
			URL url = new URL("http://open.mapquestapi.com/geocoding/v1/address?key=ODm265uTAjAivup5xUtgPQLP2GIJsg05&location=" + city + ",India");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			if (connection.getResponseCode() != 200) {
			            System.out.println("NULL");
			        }

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			        String inputLine;
			        StringBuffer response = new StringBuffer();

			        while ((inputLine = in.readLine()) != null) {
			            response.append(inputLine);
			        }
			        in.close();
			       
			        String str = response.toString();
			        JSONObject json = new JSONObject(str);
			        String str1 = new String((json.get("results")).toString());        
			        JSONArray jsonArray = new JSONArray(str1);
			        JSONObject object = jsonArray.getJSONObject(0);
			        String str2 = new String((object.get("locations")).toString());        
			        JSONArray jsonArray1 = new JSONArray(str2);
			        JSONObject object1 = jsonArray1.getJSONObject(0);
			        String str3 = new String((object1.get("displayLatLng")).toString());
			        JSONObject object2 = new JSONObject(str3);
			       
			        List<Double> list = new ArrayList<Double>();
			        list.add((Double) object2.get("lng"));
			        list.add((Double) object2.get("lat"));
			       
			        return list;              
			       
			       
			}

			public static double findDistAndCoord(String city1, String city2) throws Exception{

			Double lat1, lat2, lon1, lon2;
			List<Double> list = new ArrayList<Double>();
			list = Coordinates(city1);
			lon1 = list.get(0);
			lat1 = list.get(1);
			list = Coordinates(city2);
			lon2 = list.get(0);
			lat2 = list.get(1);

		    return (calDistance(lon1, lat1, lon2, lat2) + 50);


			}			

	}
